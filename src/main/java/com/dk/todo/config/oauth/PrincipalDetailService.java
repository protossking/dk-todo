package com.dk.todo.config.oauth;

import com.dk.todo.config.oauth.dto.SessionUser;
import com.dk.todo.domain.Users;
import com.dk.todo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository repository;

    public PrincipalDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users principal = repository.findByEmail(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다");
                });

        return new SessionUser(Long.toString(principal.getId()), principal.getEmail(),principal.getName(), principal.getPassword(), principal.getRole());

    }
}