package com.ssg.meowwms.controller.user;

import com.ssg.meowwms.dto.user.UserDTO;
import com.ssg.meowwms.security.SecurityUtils;
import com.ssg.meowwms.security.UserSecurityService;
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
        UserDTO userDTO = userService.getOne(SecurityUtils.getCurrentUserDetails().getUsername()).orElse(null);
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

    /**
     * 이메일 변경하는 API
     * @param newEmail
     */
    @PostMapping("/change-email")
    @ResponseBody
    public void changeEmail(@RequestParam("newEmail")String newEmail){
        UserDTO userDTO = userService.getOne(SecurityUtils.getCurrentUserDetails().getUsername()).orElse(null);
        userDTO.setEmail(newEmail);
        userService.modify(userDTO);
    }

    /**
     * 전화번호 변경하는 API
     * @param newTel
     */
    @PostMapping("/change-tel")
    @ResponseBody
    public void changeTel(@RequestParam("newTel")String newTel){
        UserDTO userDTO = userService.getOne(SecurityUtils.getCurrentUserDetails().getUsername()).orElse(null);
        userDTO.setTel(newTel);
        userService.modify(userDTO);
    }

    @PostMapping("/withdraw")
    @ResponseBody
    public void withdrawUser() {
        // 현재 사용자의 정보를 가져와서 탈퇴 처리를 진행합니다.
        UserDTO userDTO = userService.getOne(SecurityUtils.getCurrentUserDetails().getUsername()).orElse(null);
            // 회원 상태를 Inactive로 변경합니다.
            userDTO.setSid(2);
            userService.modify(userDTO);
    }


}
