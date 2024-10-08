package com.oauth.sample.controller;

import com.oauth.sample.model.User;
import com.oauth.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user-info")
    public Map<String, Object> user(
            @AuthenticationPrincipal OAuth2User principal) {


        User user = userService.processOAuthPostLogin(principal);

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("name", user.getName());
        userInfo.put("email", user.getEmail());
        userInfo.put("picture", user.getImgUrl());

        return userInfo;
    }
}
