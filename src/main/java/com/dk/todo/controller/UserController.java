package com.dk.todo.controller;

import com.dk.todo.domain.dto.SignupForm;
import com.dk.todo.domain.dto.UserDTO;
import com.dk.todo.domain.response.ApiResponse;
import com.dk.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ApiResponse<String> loginSuccess(@RequestBody Map<String, String> loginForm) {
        return ApiResponse.createSuccess(userService.login(loginForm.get("email"), loginForm.get("password")));
    }

    @PostMapping("/signup")
    public ApiResponse<Long> signup(@RequestBody SignupForm signupForm) {
        return ApiResponse.createSuccess(userService.signup(signupForm));
    }

    @GetMapping("/signup/check/{email}/exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        return ResponseEntity.ok(userService.checkEmailExists(email));
    }

    @PatchMapping("/{id}")
    public ApiResponse<UserDTO.UserUpdateResponse> updateUser(@PathVariable(value = "id") Long userId, @RequestBody UserDTO.UserUpdateRequest userUpdateRequest) {

        return ApiResponse.createSuccess(userService.updateUser(userId, userUpdateRequest));
    }


}