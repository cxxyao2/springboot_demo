package com.jane.springboot_jane;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();


//        http.csrf(customizer -> customizer.disable());
//        http.authorizeHttpRequests(request ->  request.anyRequest().authenticated() );
////        http.formLogin(Customizer.withDefaults());  // browser
//        http.httpBasic(Customizer.withDefaults());  // postman tool
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////
//        Customizer<CsrfConfigurer<HttpSecurity>> custCsrf = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> customizer) {
//                customizer.disable();
//            }
//        };
//
//        http.csrf(custCsrf);
//
//        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User
                .withDefaultPasswordEncoder()
                .username("jane1")
                .password("123")
                .roles("USERS")
                .build();

        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("jane2")
                .password("123")
                .roles("USERS")
                .build();


        return new InMemoryUserDetailsManager(user1, user2);
    }

}
