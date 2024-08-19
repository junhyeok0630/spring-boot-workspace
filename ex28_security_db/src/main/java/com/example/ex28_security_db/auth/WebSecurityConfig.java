package com.example.ex28_security_db.auth;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/").permitAll()
                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/guest/**").permitAll()
                .requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login-form")
                        .loginProcessingUrl("/security-check") // 없을 시 순환 참조 (타임리프 액션이 security check이 아닌 login_form으로
                                                               // 가서 오류 발생) - 스프링 시큐리티에서 제공
                        .defaultSuccessUrl("/", true)
                        // .failureUrl("/login-error")
                        .failureHandler(customAuthenticationFailureHandler)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());

        return http.build();
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("select name, password, enabled from user_list where name = ?");
        users.setAuthoritiesByUsernameQuery("select name, authority from user_list where name = ?");
        return users;
    }

    // ! 평문 비밀번호 인코딩을 위한 커스텀 인코더
    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new PasswordEncoder() {
    //         @Override
    //         public String encode(CharSequence rawPassword) {
    //             return rawPassword.toString();
    //         }
    //         @Override
    //         public boolean matches(CharSequence rawPassword, String encodedPassword) {
    //         return rawPassword.toString().equals(encodedPassword);
    //         }   
    //     };
    // }  

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    }
}
