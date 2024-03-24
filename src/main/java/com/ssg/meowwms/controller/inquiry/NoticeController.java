package com.ssg.meowwms.controller.inquiry;

import com.ssg.meowwms.dto.inquiry.NoticeDTO;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.search.OptionList;
import com.ssg.meowwms.service.inquiry.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/views/inquiry")
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

//    @GetMapping("noticeData")
//    @ResponseBody
//    public List<NoticeDTO> getInquiryData() {
//        return noticeService.selectAllNotices(null);
//    }
}

