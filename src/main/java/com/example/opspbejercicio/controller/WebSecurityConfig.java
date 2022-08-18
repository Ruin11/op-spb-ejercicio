package com.example.opspbejercicio.controller;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/api/laptops").permitAll()
        .antMatchers("/api/laptops/{id}").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .and()
        .httpBasic();
        return http.build();
    }

    @Bean
    protected void authenticationManager(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        .withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
        .and()
        .withUser("admin").password(passwordEncoder().encode("12345")).roles("ADMIN");
    } 

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception{
        return userDetailsServiceBean();

    }
    
}

