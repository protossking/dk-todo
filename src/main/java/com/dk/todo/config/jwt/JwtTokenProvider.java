package com.dk.todo.config.jwt;

import com.dk.todo.config.oauth.dto.SessionUser;
import com.dk.todo.domain.users.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;

    @Autowired
    private UserRepository userRepository;


    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        this.key = Keys.hmacShaKeyFor(secretByteKey);
    }

    public String generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

        String email = (String) oauth2User.getAttributes().get("email");

        Long userId = userRepository.findByEmail(email).get().getId();


        //Access Token 생성
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("auth", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String accessToken) {
        //토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        String userId = (claims.get("userId")).toString();
        String email = (claims.get("sub")).toString();
//        UserDetails principal = new User(claims.getSubject(), "", authorities);
        UserDetails principal = new SessionUser(Long.parseLong(userId), email);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public void validateToken(String token) throws IOException {

        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {

            return e.getClaims();
        }
    }


}