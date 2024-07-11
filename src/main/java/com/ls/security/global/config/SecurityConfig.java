package com.ls.security.global.config;

import com.ls.security.global.user.service.impl.UserServiceImpl;
import com.ls.security.global.util.AuthEntryPoint;
import com.ls.security.global.util.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {
    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        System.out.println("authenticationProvider()");
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder()); // 암호화 설정이 필요한 경우
        return provider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthEntryPoint authEntryPoint) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.and())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/v1/lighting_solutions/security/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/lighting_solutions/security/android/login").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authEntryPoint))
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        System.out.println("securityFilterChain()");

        return http.build();
    }


    // 클래스 전역 CORS 필터 추가
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // 출처를 명시적으로 허용
//        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//        config.setAllowedOrigins(Arrays.asList("http://10.0.2.2:5554"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(false);
        config.applyPermitDefaultValues();

        source.registerCorsConfiguration("/**", config); // 하위 엔드포인트까지 허용

        return source;

    }
}
