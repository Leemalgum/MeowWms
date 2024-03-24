package com.ssg.meowwms.controller.inquiry;

import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.inquiry.InquiryDTO;
import com.ssg.meowwms.dto.inquiry.NoticeDTO;
import com.ssg.meowwms.dto.search.OptionList;
import com.ssg.meowwms.service.inquiry.InquiryService;
import com.ssg.meowwms.service.inquiry.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/views/inquiry")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;
    private final NoticeService noticeService;

    @GetMapping("/inquiry")
    public void showInquiries(Model model, OptionList optionList) {
        List<InquiryDTO> inquiries = inquiryService.selectAllInquiries(null); // OptionDTO 로직은 예시로 생략
        List<NoticeDTO> notices = noticeService.selectAllNotices(null); // OptionDTO 로직은 예시로 생략

        model.addAttribute("inquiries", inquiries);
        model.addAttribute("notices", notices);
    }
}
