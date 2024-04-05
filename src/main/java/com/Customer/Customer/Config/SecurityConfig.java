package com.Customer.Customer.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public UserDetailsService getDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider provider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        //Demo Admin and User
//        UserDetails normal= User.withUsername("Nayan")
//                .password(passwordEncoder().encode("Nayan123"))
//                .roles("NORMAL")
//                .build();
//
//        UserDetails admin= User.withUsername("Rajawat")
//                .password(passwordEncoder().encode("Rajawat123)"))
//                .roles("ADMIN")
//                .build();
//
//
//        InMemoryUserDetailsManager memoryUserDetailsManager=new InMemoryUserDetailsManager(normal,admin);
//
//        return memoryUserDetailsManager;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws  Exception{
        httpSecurity.csrf().disable()
                .authorizeHttpRequests((authorize)->authorize.anyRequest().authenticated()).httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }


}
