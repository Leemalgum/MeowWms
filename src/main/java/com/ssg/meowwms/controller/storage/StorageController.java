package com.ssg.meowwms.controller.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 입고 관리
 */
@Controller
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {

    /**
     * 입고 요청 페이지를 불러옵니다.
     *
     * @return
     */
    @GetMapping("/request")
    public String getRequest() {
        return "views/storage/request";
    }
}
