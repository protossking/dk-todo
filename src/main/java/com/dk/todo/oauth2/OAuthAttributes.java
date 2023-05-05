package com.dk.todo.oauth2;

import com.dk.todo.entity.Member;
import com.dk.todo.entity.enums.Role;
import com.dk.todo.entity.enums.SocialType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class OAuthAttributes {

    private String nameAttributeKey;
    private OAuth2MemberInfo oAuth2MemberInfo;


    @Builder
    public OAuthAttributes (String nameAttributeKey, OAuth2MemberInfo oAuth2MemberInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2MemberInfo = oAuth2MemberInfo;
    }


    public static OAuthAttributes of(SocialType socialType, String usernameAttributeName, Map<String, Object> attributes) {

        if(socialType == SocialType.NAVER) {
            return ofNaver(usernameAttributeName, attributes);
        }

        return null;
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2MemberInfo(new NaverOAuth2MemberInfo(attributes))
                .build();
    }

    public Member toEntity(SocialType socialType, OAuth2MemberInfo oAuth2MemberInfo) {
        return Member.builder()
                .socialType(socialType)
                .socialId(oAuth2MemberInfo.getId())
                .email(UUID.randomUUID() + "@socialMember.com")
                .nickname(oAuth2MemberInfo.getNickname())
                .profileImg(oAuth2MemberInfo.getImageUrl())
                .role(Role.GUEST)
                .build();
    }


}
