package com.example.ex27_security_status_check.auth;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
            
                String loginId = request.getParameter("username");
                String errorMsg = "";

                if(exception instanceof BadCredentialsException) {
                   errorMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.(BadCredentialsException)";
                } else if(exception instanceof InternalAuthenticationServiceException) {
                    errorMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.(InternalAuthenticationServiceException)";
                }

                request.setAttribute("username", loginId);
                request.setAttribute("error_message", errorMsg);
                request.setAttribute("login_failure", true);

                request.getRequestDispatcher("/login-form").forward(request, response);
            }
    
}
