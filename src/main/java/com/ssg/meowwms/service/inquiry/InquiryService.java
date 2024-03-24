package com.ssg.meowwms.service.inquiry;

import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.inquiry.InquiryDTO;

import java.util.List;

public interface InquiryService {
    void insertInquiry(InquiryDTO inquiry);
    List<InquiryDTO> selectAllInquiries(List<OptionDTO> options);
    InquiryDTO selectInquiry(int postNum);
    void deleteInquiry(int postNum);
    void updateInquiry(InquiryDTO inquiry);
}
