package com.oauth.sample.service;

import com.oauth.sample.model.User;
import com.oauth.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User processOAuthPostLogin(OAuth2User principal) {
        String email = principal.getAttribute("email");

        Optional<User> existingUser = userRepository.findByEmail(email);


        if (existingUser.isPresent()) {
            User user = existingUser.get();
            return userRepository.save(user);
        } else {
            String platform = extractOAuth2Platform();


//            newUser.setEmail(email);
//            newUser.setName(principal.getAttribute("name"));
//            newUser.setImgUrl(principal.getAttribute("picture"));
//            newUser.setLoginSource();

            User newUser = setUpNewUser(principal, platform);

            return userRepository.save(newUser);

        }
    }

    @Override
    public String extractOAuth2Platform() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        return authToken.getAuthorizedClientRegistrationId();
    }

    @Override
    public User setUpNewUser(OAuth2User principal, String platform) {
        if (platform.equals("google")) return processGoogleOAuthLogin(principal, platform);
        if (platform.equals("github")) return processGithubOAuthLogin(principal, platform);
        return null;
    }

    @Override
    public User processGoogleOAuthLogin(OAuth2User principal, String platform) {
        

        return null;
    }

    @Override
    public User processGithubOAuthLogin(OAuth2User principal, String oAuth2Platform) {
        return null;
    }
}
