package com.ssg.meowwms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin((formLogin) -> formLogin
                        .failureUrl("/views/user/login?error") // 로그인 실패시 이동할 페이지
                        .loginPage("/views/user/login") // 로그인 페이지 설정
//                        .defaultSuccessUrl("/user/dashboard"))// 로그인 성공시 이동할 페이지)
                .successHandler(customAuthenticationSuccessHandler)
                )
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/views/user/logout")) // 로그아웃 url 설정
                        .logoutSuccessUrl("/views/user/login") // 로그아웃 성공 시 이동할 url
                        .invalidateHttpSession(true)) // 기존에 생성된 사용자 세션도 invalidateHttpSession 을 통해 삭제하도록 처리//

        ;

        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/bootstrap/**").permitAll()
                        .requestMatchers("/", "/views/user/login","/views/user/register","/views/user/findId","/views/user/findPw","/user/**", "/item/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
                );

//        http.authorizeRequests()
//                .requestMatchers("/bootstrap/**").permitAll()
//                .requestMatchers("/", "/views/**", "/item/**", "/images/**").permitAll()
////                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers("/views/user/login").hasAuthority("USER")
//                .anyRequest().authenticated()
//        ;

        http.csrf(AbstractHttpConfigurer::disable);

        http.exceptionHandling(authenticationManager -> authenticationManager
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    //resources 접근
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
