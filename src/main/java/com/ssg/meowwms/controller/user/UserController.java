package com.ssg.meowwms.controller.user;

import com.ssg.meowwms.dto.user.UserDTO;
import com.ssg.meowwms.security.UserSecurityService;
import com.ssg.meowwms.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/views/user")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;
    private final UserSecurityService userSecurityService;

    /**
     * 로그인 화면
     */
    @GetMapping("/login")
    public void loginGet() {

    }

    /**
     * 로그인 요청
     *
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        System.out.println(username + " " + password);
        return "redirect:/views/user/index";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/views/user/login";
    }

    @GetMapping("/index")
    public void index() {

    }

    /**
     * 회원가입 화면
     */
    @GetMapping("/register")
    public void registerGet() {

    }

    /**
     * 회원가입 요청
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/register")
    public String registerPost(@ModelAttribute UserDTO userDTO) {
        userService.register(userDTO);
        log.info(userDTO);
        return "redirect:/views/user/login";
    }

    /**
     * 로그인한 회원의 정보를 가져오는 JSON으로 제공하는 API
     */
    @GetMapping("/getUserData")
    @ResponseBody
    public UserDTO getUserData() {
//        SecurityUtils.getCurrentUserDetails().getUsername();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String uid = userDetails.getUsername();
        UserDTO userDTO = userService.getOne(uid).orElse(null);
        // 가져온 사용자 정보를 JSON 형식으로 응답합니다.
        log.info(userDTO);
        return userDTO;
    }

    @GetMapping("/myInfo")
    public String getOne() {
        return "views/user/myInfo";
    }

    /**
     * 쇼핑몰 정보 보기
     */
    @GetMapping("/myBusinessInfo")
    public void getBusinessInfo() {

    }

    /**
     * 회원 조회
     */
    @GetMapping("/memberList")
    public void getList() {

    }
}
