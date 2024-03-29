package com.dk.todo.domain.users;

import com.dk.todo.api.users.request.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    private String provider;

    private String profileImg;

    private String backgroundImg;

    private String twitterUrl;

    private String instagramUrl;

    private String facebookUrl;




    @Builder
    public Users(Long id, String name, String email, String password, Role role, String provider) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.provider = provider;
    }

    public Users update(String name, String provider) {
        this.name = name;
        this.provider = provider;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public void updateUser(UserDTO.UserUpdateRequest userUpdateRequest) {
        this.name = userUpdateRequest.getName();
        this.facebookUrl = userUpdateRequest.getFacebookUrl();
        this.twitterUrl = userUpdateRequest.getTwitterUrl();
        this.instagramUrl = userUpdateRequest.getInstagramUrl();
    }

    public void updateUserWithImageFile(UserDTO.UserUpdateRequest userUpdateRequest, String fileUrl) {

        this.name = userUpdateRequest.getName();
        this.facebookUrl = userUpdateRequest.getFacebookUrl();
        this.twitterUrl = userUpdateRequest.getTwitterUrl();
        this.instagramUrl = userUpdateRequest.getInstagramUrl();
        this.profileImg = fileUrl;
    }
}

