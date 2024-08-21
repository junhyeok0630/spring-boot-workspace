package com.example.ex31_jpa_qnaboard_rest_api_security.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByEmail(String email);
    UserEntity findByEmailAndPassword(String email, String password);
}