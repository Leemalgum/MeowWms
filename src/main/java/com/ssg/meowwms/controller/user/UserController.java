package com.ssg.meowwms.controller.user;

import com.ssg.meowwms.dto.user.UserDTO;
import com.ssg.meowwms.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/views/user")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;

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
    public String loginPost() {
//        userService
//        log.info(userDTO);
        return "redirect:/views/user/index";
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
     * 내 정보 보기
     */
    @GetMapping("/myInfo")
    public void getOne() {

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
