package com.oauth.sample.service;


import com.oauth.sample.model.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserService {
    User processOAuthPostLogin(OAuth2User principal);
}
