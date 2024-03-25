package com.ssg.meowwms.controller.warehouse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 창고 관리
 */
@Controller
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    /**
     * 창고 등록 페이지를 불러옵니다.
     *
     * @return
     */
    @GetMapping("/register")
    public String getRegister() {
        return "views/warehouse/register";
    }

    /**
     * 창고 목록 페이지를 불러옵니다.
     *
     * @return
     */
    @GetMapping("/list")
    public String getList() {
        return "views/warehouse/list";
    }

    /**
     * 창고 상세 페이지를 불러옵니다.
     *
     * @return
     */
    @GetMapping("/read")
    public String getRead() {
        return "views/warehouse/read";
    }
}
