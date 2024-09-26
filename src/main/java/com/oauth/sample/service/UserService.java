package com.oauth.sample.service;


import com.oauth.sample.model.User;

public interface UserService {
    User processOAuthPostLogin(String email, String name);
}
