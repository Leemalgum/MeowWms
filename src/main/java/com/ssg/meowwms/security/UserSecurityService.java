package com.ssg.meowwms.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ssg.meowwms.dto.user.UserDTO;
import com.ssg.meowwms.service.user.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserDTO> userDTOOptional = this.userService.getOne(userId);
        if (!userDTOOptional.isPresent()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        UserDTO meowUser = userDTOOptional.get();

        // 권한 리스트 생성
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 사용자의 rid를 기반으로 권한 부여
        UserRole userRole = findRoleByRid(meowUser.getRid());
        authorities.add(new SimpleGrantedAuthority(userRole.getValue()));

        // Spring Security의 User 객체를 생성하여 반환
        return new User(meowUser.getUname(), meowUser.getUpw(), authorities);
    }

    private UserRole findRoleByRid(int rid) {
        for (UserRole role : UserRole.values()) {
            if (role.getRid() == rid) {
                return role;
            }
        }
        // 기본적으로 USER 권한을 반환하거나, 예외 처리를 할 수 있습니다.
        return UserRole.USER;
    }
}
