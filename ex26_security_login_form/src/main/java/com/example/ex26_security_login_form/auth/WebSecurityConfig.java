package com.example.ex26_security_login_form.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
        .requestMatchers("/").permitAll()
        .requestMatchers("/css/**", "/js/**","/img/**").permitAll()
        .requestMatchers("/guest/**").permitAll()
        .requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated())
        .formLogin(formLogin -> formLogin.permitAll()
                        .loginPage("/login-form")
                        .defaultSuccessUrl("/member/welcome", true)
                        .failureUrl("/login-error")
                        .permitAll()
        )
        .logout(logout -> logout.permitAll());
        
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
            .password(passwordEncoder()
                .encode("1234"))
            .roles("USER")
            .build();


        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder()
                .encode("1234"))
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
