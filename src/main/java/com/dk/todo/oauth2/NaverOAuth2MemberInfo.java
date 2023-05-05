package com.dk.todo.oauth2;


import java.util.Map;

public class NaverOAuth2MemberInfo extends OAuth2MemberInfo{

    public NaverOAuth2MemberInfo(Map<String, Object> attributes) {
        super(attributes);
    }


    @Override
    public String getId() {

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if(response == null) {
            return null;
        }

        return (String) response.get("id");
    }

    @Override
    public String getNickname() {

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if(response == null) {
            return null;
        }

        return (String) response.get("nickname");
    }

    @Override
    public String getImageUrl() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if (response == null) {
            return null;
        }

        return (String) response.get("profile_image");
    }
}
