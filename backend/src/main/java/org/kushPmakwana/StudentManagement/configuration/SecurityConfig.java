package org.kushPmakwana.StudentManagement.configuration;

import com.sun.net.httpserver.HttpServer;
import org.kushPmakwana.StudentManagement.exceptions.AdminNotFoundException;
import org.kushPmakwana.StudentManagement.exceptions.UserDisabledException;
import org.kushPmakwana.StudentManagement.models.Admin;
import org.kushPmakwana.StudentManagement.models.User;
import org.kushPmakwana.StudentManagement.repository.AdminRepository;
import org.kushPmakwana.StudentManagement.repository.UserRepository;
import org.kushPmakwana.StudentManagement.security.UserPrincipal;
import org.kushPmakwana.StudentManagement.security.users.AdminUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req
                                .requestMatchers("/auth/login", "/logout", "/auth/**")
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(
            UserRepository userRepository,
            AdminRepository adminRepository
    ){
        return email -> {
            User user = userRepository.findByEmail(email).orElseThrow(() ->
                                        new UsernameNotFoundException("User with email " + email + " not registered"));

            if(!user.isEnabled()){
                throw new UserDisabledException("User is disabled");
            }

            switch (user.getRole()){
                case ADMIN -> {
                    Admin admin = adminRepository.findByUserEmail(email)
                            .orElseThrow(() ->
                                    new AdminNotFoundException("Admin with user email " + email + " is not registered"));
                    return new UserPrincipal(
                            email,
                            user.getRole(),
                            user.getPassword(),
                            new AdminUser(
                                    admin.getId(),
                                    admin.getAdminName(),
                                    email,
                                    admin.getAdminCode()
                            )
                    );
                }

                default -> throw new UsernameNotFoundException("User with email " + email + " doesn't exists");
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);

        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
