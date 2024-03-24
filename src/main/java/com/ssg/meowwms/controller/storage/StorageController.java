package com.ssg.meowwms.controller.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {

    @GetMapping("/request")
    public String getRequest() {
        return "views/storage/request";
    }
}
