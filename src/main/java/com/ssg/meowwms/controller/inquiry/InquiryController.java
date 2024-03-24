package com.ssg.meowwms.controller.inquiry;

import com.ssg.meowwms.dto.inquiry.InquiryDTO;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.search.OptionList;
import com.ssg.meowwms.service.inquiry.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/views/inquiry")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    // 데이터를 JSON으로 제공하는 API
    @GetMapping("/data")
    @ResponseBody
    public List<InquiryDTO> getInquiryData(
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String searchKeyword) {
        OptionList options = new OptionList();
        options.add(new OptionDTO(searchType, searchKeyword));
        System.out.println(options);
        return inquiryService.selectAllInquiries(options.getOptionList());
    }

    // 사용자 인터페이스(UI)를 제공하는 뷰 페이지
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/inquiry")
    public String showInquiryPage() {
        return "views/inquiry/inquiry";
    }

    @GetMapping("/modify-content/{postNum}")
    public String showModifyPage(@PathVariable Optional<Long> postNum, Model model) {
        if (postNum.isPresent()) {
            // postNum에 해당하는 게시글 정보를 조회하여 model에 추가
            // 예: model.addAttribute("post", postService.getPostDetails(postNum.get()));
        }
        // 수정 페이지 또는 글쓰기 페이지로 이동
        return "/views/inquiry/modify-content";
    }

    @GetMapping("/modify-content")
    public String showCreatePage(Model model) {
        // 글쓰기 페이지로 이동, 비어 있는 InquiryDTO 객체를 추가
        model.addAttribute("inquiry", new InquiryDTO());
        return "/views/inquiry/modify-content";
    }

    @GetMapping("/read-content/{postNum}")
    public String showDetailPage(@PathVariable int postNum, Model model){
        model.addAttribute("inquiryDTO", inquiryService.selectInquiry(postNum));

        return "/views/inquiry/read-content";
    }

    @PostMapping("/modify-content")
    public String submitForm(InquiryDTO inquiry, Model model) {
        inquiry.setUserId("admin"); // 사용자 ID는 임시로 "admin"으로 설정
        if (inquiry.getPostNum() == 0) { // postNum이 0이면 새 글 작성
            inquiryService.insertInquiry(inquiry);
        } else { // postNum이 0이 아니면 기존 글 수정
            inquiryService.updateInquiry(inquiry);
        }
        return "redirect:/views/inquiry/inquiry"; // 글 목록 페이지로 리다이렉트
    }
}
