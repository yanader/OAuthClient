package com.oauth.sample.repository;

import com.oauth.sample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserInterface extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
