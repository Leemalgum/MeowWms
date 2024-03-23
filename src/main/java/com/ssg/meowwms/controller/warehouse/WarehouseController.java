package com.ssg.meowwms.controller.warehouse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    @GetMapping("/register")
    public String getRegister() {

        return "views/warehouse/register";
    }

    @GetMapping("/read")
    public String getRead() {
        return "views/warehouse/read";
    }
}
