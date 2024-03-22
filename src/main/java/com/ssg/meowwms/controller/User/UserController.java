package com.ssg.meowwms.controller.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    // 로그인 페이지로 이동
    @GetMapping("/user/login")
    public void login() {

    }
}
