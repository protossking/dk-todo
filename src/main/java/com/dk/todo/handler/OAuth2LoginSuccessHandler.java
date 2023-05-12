package com.dk.todo.handler;

import com.dk.todo.domain.enums.Role;
import com.dk.todo.jwt.service.JwtService;
import com.dk.todo.oauth2.CustomOAuth2Member;
import com.dk.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login Success");
        try{
            CustomOAuth2Member oAuth2Member = (CustomOAuth2Member) authentication.getPrincipal();

            if(oAuth2Member.getRole() == Role.GUEST) {
                String accessToken = jwtService.createAccessToken(oAuth2Member.getEmail());
                response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);
                response.sendRedirect("oauth2/sign-up");

                jwtService.sendAccessAndRefreshToken(response, accessToken, null);

            }
            else {
                loginSuccess(response, oAuth2Member);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void loginSuccess(HttpServletResponse response, CustomOAuth2Member oAuth2Member) throws IOException {

        String accessToken = jwtService.createAccessToken(oAuth2Member.getEmail());
        String refreshToken = jwtService.createRefreshToken();

        response.setHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);
        response.addHeader(jwtService.getRefreshHeader(), "Bearer " + refreshToken);

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        jwtService.updateRefreshToken(oAuth2Member.getEmail(), refreshToken);
    }
}
