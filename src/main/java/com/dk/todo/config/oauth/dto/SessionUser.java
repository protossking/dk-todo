package com.dk.todo.config.oauth.dto;

import com.dk.todo.domain.Users;
import com.dk.todo.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter

public class SessionUser implements UserDetails {


    private Long id;
    private String email;
    private String name;
    private String password;
    private Role authority;

    @JsonIgnore
    public SessionUser(Long id, String email, String name, String password, Role authority) {

        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.authority = authority;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections = new ArrayList<>();

        collections.add(() -> {
            return "ROLE_" + authority;
        });

        return collections;


    }

    @Override
    public String getPassword() {
//        return user.getPassword();
        return password;
    }

    @Override
    public String getUsername() {
//        return user.getName();
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}