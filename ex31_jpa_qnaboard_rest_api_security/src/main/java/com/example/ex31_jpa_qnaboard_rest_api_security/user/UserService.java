package com.example.ex31_jpa_qnaboard_rest_api_security.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ex31_jpa_qnaboard_rest_api_security.DataNotFoundException;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

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

    public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
        final UserEntity originalUser = userRepository.findByEmail(email);

        if (originalUser != null && encoder.matches(password, originalUser.getPassword())){
            return originalUser;
        }
        return null;
    }

    public UserEntity getUser(String id) {
        Optional<UserEntity> user = this.userRepository.findById(Long.parseLong(id));
        
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }
}
