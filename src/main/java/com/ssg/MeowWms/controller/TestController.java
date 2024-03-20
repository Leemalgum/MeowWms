package com.ssg.MeowWms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/common/fragments/index")
    public void test(){

    }
}
