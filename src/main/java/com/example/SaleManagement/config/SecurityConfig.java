package com.example.SaleManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}test123")
                .roles("ADMIN", "STAFF")
                .build();

        UserDetails staff = User.builder()
                .username("staff")
                .password("{noop}test123")
                .roles("STAFF")
                .build();
        return new InMemoryUserDetailsManager(admin, staff);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HttpSecurity httpSecurity) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/images/**","/css/**","/js/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .defaultSuccessUrl("/index", true)
                        .permitAll())
                .logout(logout -> logout.permitAll());
        return http.build();
    }

//
//    // mã hóa mật khẩu người
////    @Bean
////    public PasswordEncoder passwordEncoder(){
////        return new BCryptPasswordEncoder();
////    }
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////        UserDetails userDetails = User.withDefaultPasswordEncoder()
////                .username("user")
////                .password("password")
////                .roles("USER")
////                .build();
////
////        return new InMemoryUserDetailsManager(userDetails);
////    }
}
