package com.ssg.meowwms.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
@Log4j2
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 현재 로그인한 사용자의 권한 정보를 가져옵니다.
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // 권한 정보를 반복하여 사용자의 권한을 확인합니다.
        for (GrantedAuthority authority : authorities) {
            String authorityName = authority.getAuthority();
            // 권한 이름을 확인하여 필요한 작업을 수행합니다.
            if ("ROLE_ADMIN".equals(authorityName)) {
                // ADMIN 권한이 있는 경우
                response.sendRedirect("/user/dashboard");
                log.info("Admin user logged in");
                return;
            }
        }
        // ADMIN 권한이 없는 경우
        response.sendRedirect("/user/dash-user");
        log.info("Regular user logged in");
    }
}
