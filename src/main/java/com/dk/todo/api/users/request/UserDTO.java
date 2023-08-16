package com.dk.todo.api.users.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserDTO {



    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserUpdateRequest {

        private String name;
        private String twitterUrl;
        private String facebookUrl;
        private String instagramUrl;

    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserUpdateResponse {

        private String name;
        private String twitterUrl;
        private String facebookUrl;
        private String instagramUrl;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDetailResponse {

        private Long id;
        private String email;
        private String facebookUrl;
        private String instagramUrl;
        private String twitterUrl;
        private String name;
        private String profileImg;
    }



}
