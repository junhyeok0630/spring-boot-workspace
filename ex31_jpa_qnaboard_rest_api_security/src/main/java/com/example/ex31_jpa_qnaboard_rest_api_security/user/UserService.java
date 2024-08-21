package com.example.ex31_jpa_qnaboard_rest_api_security.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserEntity create(final UserEntity user) {
        if (user == null || user.getUsername() == null) {
            throw new RuntimeException("Invalid arguments");
        }

        final String email = user.getEmail();
        if(userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        return userRepository.save(user);
    }

    public UserEntity getByCredentials(final String email, final String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
