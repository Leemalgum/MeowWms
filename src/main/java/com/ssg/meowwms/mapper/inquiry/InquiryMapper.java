package com.ssg.meowwms.mapper.inquiry;

import com.ssg.meowwms.domain.inquiry.InquiryVO;
import com.ssg.meowwms.dto.OptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import java.util.List;

@Mapper
public interface InquiryMapper {
    void insertInquiry(InquiryVO inquiry);
    List<InquiryVO> selectAllInquiries(@Param("options") List<OptionDTO> options);
    InquiryVO selectInquiry(int postNum);
    void deleteInquiry(int postNum);
    void updateInquiry(InquiryVO inquiry);
}