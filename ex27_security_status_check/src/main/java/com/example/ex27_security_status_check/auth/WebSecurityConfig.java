package com.example.ex27_security_status_check.auth;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
        .requestMatchers("/").permitAll()
        .requestMatchers("/css/**", "/js/**","/img/**").permitAll()
        .requestMatchers("/guest/**").permitAll()
        .requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated())
        .formLogin(formLogin -> formLogin
                        .loginPage("/login-form")
                        .loginProcessingUrl("/security-check") // 없을 시 순환 참조 (타임리프 액션이 security check이 아닌 login_form으로 가서 오류 발생) - 스프링 시큐리티에서 제공
                        .defaultSuccessUrl("/member/welcome", true)
                        // .failureUrl("/login-error")
                        .failureHandler(customAuthenticationFailureHandler) 
                        .permitAll()
        )
        .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .permitAll());
        
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
