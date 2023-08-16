package com.dk.todo.api.users;

import com.dk.todo.api.users.request.UserDTO;
import com.dk.todo.config.oauth.dto.SessionUser;
import com.dk.todo.response.ApiResponse;
import com.dk.todo.api.users.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

//    @PostMapping("/signup")
//    public ApiResponse<Long> signup(@RequestBody SignupForm signupForm) {
//        return ApiResponse.createSuccess(userService.signup(signupForm));
//    }

    @GetMapping("/signup/check/{email}/exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        return ResponseEntity.ok(userService.checkEmailExists(email));
    }

    @PatchMapping(value = "/update",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<UserDTO.UserUpdateResponse> updateUser(@RequestPart(value = "userUpdateRequest") UserDTO.UserUpdateRequest userUpdateRequest,
                                                              @RequestPart(value = "multipartFile", required = false) MultipartFile multipartFile,
                                                              @Parameter(hidden = true) @AuthenticationPrincipal SessionUser sessionUser) {

        return ApiResponse.createSuccess(userService.updateUser(userUpdateRequest, multipartFile, sessionUser.getId()));
    }

    @GetMapping("")
    public ApiResponse<UserDTO.UserDetailResponse> getUser(@Parameter(hidden = true) @AuthenticationPrincipal SessionUser sessionUser) {
        return ApiResponse.createSuccess(userService.findUser(sessionUser.getId()));
    }


}