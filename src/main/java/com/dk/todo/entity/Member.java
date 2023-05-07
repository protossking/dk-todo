package com.dk.todo.entity;

import com.dk.todo.entity.enums.Role;
import com.dk.todo.entity.enums.SocialType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("이메일")
    @Column(name = "email")
    private String email;

    @Comment("비밀번호")
    @Column(name = "password")
    private String password;

    @Comment("닉네임")
    @Column(name = "nickname")
    private String nickname;

    @Comment("사는곳")
    @Column(name = "city")
    private String city;

    @Comment("유저 권한")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Comment("소셜 타입")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Comment("소셜 식별자 값")
    @Column(name = "social_id")
    private String socialId;

    @Comment("프로필 사진")
    @Column(name = "profile_img")
    private String profileImg;

    @Comment("배경사진")
    @Column(name = "backgroud_img")
    private String backgroundImg;

    @Comment("트위터 URL")
    @Column(name = "twitter_url")
    private String twitterUrl;

    @Comment("인스타그램 URL")
    @Column(name = "instagram_url")
    private String instagramUrl;

    @Comment("페이스북 URL")
    @Column(name = "facebook_url")
    private String facebookUrl;

    @Comment("리프레쉬 토큰")
    @Column(name = "refresh_token")
    private String refreshToken;

    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    @OneToMany(mappedBy = "member")
    List<Task> taskList = new ArrayList<>();


}
