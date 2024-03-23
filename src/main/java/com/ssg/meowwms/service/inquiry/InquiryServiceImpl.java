package com.ssg.meowwms.service.inquiry;

import com.ssg.meowwms.domain.inquiry.InquiryVO;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.inquiry.InquiryDTO;
import com.ssg.meowwms.mapper.inquiry.InquiryMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryMapper inquiryMapper;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void insertInquiry(InquiryDTO inquiry) {
        InquiryVO inquiryVO = modelMapper.map(inquiry, InquiryVO.class);
        inquiryMapper.insertInquiry(inquiryVO);
    }

    @Override
    public List<InquiryDTO> selectAllInquiries(List<OptionDTO> options) {
        List<InquiryVO> list = inquiryMapper.selectAllInquiries(options);
        List<InquiryDTO> dtoList = list.stream().map(vo -> modelMapper.map(vo, InquiryDTO.class)).toList();
        return dtoList;
    }

    @Override
    public InquiryDTO selectInquiry(int postNum) {
        InquiryVO inquiryVO = inquiryMapper.selectInquiry(postNum);
        System.out.println(inquiryVO);
        InquiryDTO inquiryDTO = modelMapper.map(inquiryVO, InquiryDTO.class);
        System.out.println(inquiryDTO);
        return inquiryDTO;
    }

    @Override
    @Transactional
    public void deleteInquiry(int postNum) {
        inquiryMapper.deleteInquiry(postNum);
    }

    @Override
    @Transactional
    public void updateInquiry(InquiryDTO inquiry) {
        InquiryVO inquiryVO = modelMapper.map(inquiry, InquiryVO.class);
        inquiryMapper.updateInquiry(inquiryVO);
    }
}
