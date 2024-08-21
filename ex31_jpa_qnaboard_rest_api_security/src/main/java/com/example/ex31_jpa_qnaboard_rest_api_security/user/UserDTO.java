package com.example.ex31_jpa_qnaboard_rest_api_security.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String token;

    private Long id;

    private String username;

    private String password;

    private String email;
}