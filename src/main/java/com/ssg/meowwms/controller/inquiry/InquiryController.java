package com.ssg.meowwms.controller.inquiry;

import com.ssg.meowwms.dto.inquiry.InquiryDTO;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.search.OptionList;
import com.ssg.meowwms.security.SecurityUtils;
import com.ssg.meowwms.service.inquiry.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inquiry")
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
    @GetMapping("/inquiry")
    public String showInquiryPage() {
        return "views/inquiry/inquiry";
    }

    @GetMapping("/modify-content/{postNum}")
    public String showModifyPage(@PathVariable(required = false) Integer postNum, Model model) {
        if (postNum != null) {
            model.addAttribute("inquiry", inquiryService.selectInquiry(postNum));
        } else {
            model.addAttribute("inquiry", new InquiryDTO());
        }
        // 수정 페이지 또는 글쓰기 페이지로 이동
        return "views/inquiry/modify-content";
    }

    @PostMapping("/modify-content")
    public String submitForm(InquiryDTO inquiry) {
        if (inquiry.getPostNum() == 0) { // postNum이 0이면 새 글 작성
            inquiry.setUserId(SecurityUtils.getCurrentUserDetails().getUsername());
            inquiryService.insertInquiry(inquiry);
            return "views/inquiry/inquiry";
        } else { // postNum이 0이 아니면 기존 글 수정
            inquiryService.updateInquiry(inquiry);
        }
        return "redirect:/inquiry/read-content/" + inquiry.getPostNum();
    }

    @GetMapping("/modify-content")
    public String registerForm(Model model) {
        InquiryDTO inquiryDTO = new InquiryDTO();
        inquiryDTO.setUserId(SecurityUtils.getCurrentUserDetails().getUsername());
        model.addAttribute("inquiry",inquiryDTO);
        return "views/inquiry/modify-content"; // 글 목록 페이지로 리다이렉트
    }

    @GetMapping("/read-content/{postNum}")
    public String showDetailPage(@PathVariable Integer postNum, Model model) {
        model.addAttribute("inquiry", inquiryService.selectInquiry(postNum));
        model.addAttribute("currentUsername", SecurityUtils.getCurrentUserDetails().getUsername());
        return "views/inquiry/read-content"; // 'views/inquiry/read-content.html'로 해석됩니다.
    }


    @GetMapping("/inquiryDelete/{postNum}")
    public String deleteNotice(@PathVariable(required = false) Integer postNum){
        inquiryService.deleteInquiry(postNum);
        return "views/inquiry/inquiry";
    }

    @PostMapping("/inquiry/response")
    public ResponseEntity<?> submitResponse(
            @RequestParam("postNum") int postNum,
            @RequestParam("response") String response) {
        InquiryDTO inquiryDTO = inquiryService.selectInquiry(postNum);
        inquiryDTO.setResponse(response);
        inquiryService.updateInquiry(inquiryDTO);

        return ResponseEntity.ok().build();
    }
}
