package com.oauth.sample.service;

import com.oauth.sample.model.User;
import com.oauth.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User processOAuthPostLogin(OAuth2User principal) {
        String email = principal.getAttribute("email");

        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setLoginCount(user.getLoginCount() + 1);
            return userRepository.save(user);
        } else {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(principal.getAttribute("name"));
            newUser.setLoginCount(1);
            return userRepository.save(newUser);

        }
    }
}
