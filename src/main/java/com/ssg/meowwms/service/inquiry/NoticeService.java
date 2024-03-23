package com.ssg.meowwms.service.inquiry;

import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.inquiry.NoticeDTO;

import java.util.List;

public interface NoticeService {
    void insertNotice(NoticeDTO notice);
    List<NoticeDTO> selectAllNotices(List<OptionDTO> options);
    NoticeDTO selectNotice(int no);
    void deleteNotice(int no);
    void updateNotice(NoticeDTO notice);
}
