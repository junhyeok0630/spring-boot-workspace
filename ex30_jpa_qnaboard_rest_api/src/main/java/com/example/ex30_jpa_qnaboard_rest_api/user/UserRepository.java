package com.example.ex30_jpa_qnaboard_rest_api.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByEmail(String email);
    UserEntity findByEmailAndPassword(String email, String password);
}