package com.ssg.meowwms.controller.inquiry;

import com.ssg.meowwms.dto.inquiry.InquiryDTO;
import com.ssg.meowwms.dto.inquiry.NoticeDTO;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.search.OptionList;
import com.ssg.meowwms.security.SecurityUtils;
import com.ssg.meowwms.service.inquiry.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inquiry")
@RequiredArgsConstructor
@Log4j2
public class NoticeController {

    private final NoticeService noticeService;
    @GetMapping("/noticeData")
    public ResponseEntity<List<NoticeDTO>> getNoticeData(
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String searchKeyword) {
        OptionList options = new OptionList();
        if (searchType != null && searchKeyword != null) {
            options.add(new OptionDTO(searchType, searchKeyword));
        }
        List<NoticeDTO> data = noticeService.selectAllNotices(options.getOptionList());
        return ResponseEntity.ok(data);
    }

    @GetMapping("/read-notice/{no}")
    public String showDetailNotice(@PathVariable Integer no, Model model) {
        System.out.println(no);
        System.out.println(noticeService.selectNotice(no));
        model.addAttribute("currentUsername", SecurityUtils.getCurrentUserDetails().getUsername());
        model.addAttribute("notice", noticeService.selectNotice(no));
        return "views/inquiry/read-notice";
    }

    @GetMapping("/modify-notice/{no}")
    public String showModifyNotice(@PathVariable(required = false) Integer no, Model model) {
        if (no != null) {
            model.addAttribute("notice", noticeService.selectNotice(no));
        } else {
            NoticeDTO notice = new NoticeDTO();
            notice.setUserId(SecurityUtils.getCurrentUserDetails().getUsername());
            model.addAttribute("notice", notice);
        }
        // 수정 페이지 또는 글쓰기 페이지로 이동
        return "views/inquiry/modify-notice";
    }

    @PostMapping("/modify-notice")
    public String submitNotice(NoticeDTO notice) {
        if (notice.getNo() == 0) { // postNum이 0이면 새 글 작성
            notice.setUserId(SecurityUtils.getCurrentUserDetails().getUsername());
            noticeService.insertNotice(notice);
            return "views/inquiry/inquiry";
        } else { // postNum이 0이 아니면 기존 글 수정
            noticeService.updateNotice(notice);
        }
        return "views/inquiry/read-notice/" + notice.getNo();
    }

    @GetMapping("/modify-notice")
    public String registerForm(Model model) {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setUserId(SecurityUtils.getCurrentUserDetails().getUsername());
        model.addAttribute("notice",noticeDTO);
        return "views/inquiry/modify-notice"; // 글 목록 페이지로 리다이렉트
    }

    @GetMapping("/noticeDelete/{no}")
    public String deleteNotice(@PathVariable(required = false) Integer no){
        noticeService.deleteNotice(no);
        return "views/inquiry/inquiry";
    }

//    @GetMapping("noticeData")
//    @ResponseBody
//    public List<NoticeDTO> getInquiryData() {
//        return noticeService.selectAllNotices(null);
//    }
}

