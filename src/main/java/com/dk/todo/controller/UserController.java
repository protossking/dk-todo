package com.dk.todo.controller;

import com.dk.todo.domain.dto.SignupForm;
import com.dk.todo.domain.response.ApiResponse;
import com.dk.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ApiResponse<String> loginSuccess(@RequestBody Map<String, String> loginForm) {
        return ApiResponse.createSuccess(service.login(loginForm.get("email"), loginForm.get("password")));
    }

    @PostMapping("/signup")
    public ApiResponse<Long> signup(@RequestBody SignupForm signupForm) {
        return ApiResponse.createSuccess(service.signup(signupForm));
    }

    @GetMapping("/signup/check/{email}/exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        return ResponseEntity.ok(service.checkEmailExists(email));
    }

}