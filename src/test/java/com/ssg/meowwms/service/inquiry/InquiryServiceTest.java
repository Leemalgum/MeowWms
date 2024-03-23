package com.ssg.meowwms.service.inquiry;

import com.ssg.meowwms.domain.inquiry.InquiryVO;
import com.ssg.meowwms.dto.OptionDTO;
import com.ssg.meowwms.dto.inquiry.InquiryDTO;
import com.ssg.meowwms.mapper.inquiry.InquiryMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@Log4j2
class InquiryServiceImplTest {

    @MockBean
    private InquiryMapper inquiryMapper;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private InquiryServiceImpl inquiryService;

    private InquiryDTO inquiryDTO;
    private OptionDTO optionDTO;

    @BeforeEach
    void setUp() {
        InquiryDTO inquiryDTO = InquiryDTO.builder()
                .postNum(12345)
                .postType("Customer Service")
                .postTitle("Order Delivery Delay")
                .userId("user123")
                .postContent("My order was supposed to arrive last week, but I've not received it yet. Please advise.")
                .postDate(new Date()) // Assuming the inquiry was posted right now
                .response("Your inquiry has been received and is being reviewed by our support team.")
                .build();
        optionDTO = new OptionDTO(); // Populate with test data as needed
        inquiryService = new InquiryServiceImpl(inquiryMapper, modelMapper);
    }

    @Test
    void insertInquiry() {
        doNothing().when(inquiryMapper).insertInquiry(any());
        inquiryService.insertInquiry(inquiryDTO);
        verify(inquiryMapper, times(1)).insertInquiry(any());
    }

    @Test
    void selectAllInquiries() {
        given(inquiryMapper.selectAllInquiries(anyList())).willReturn(Collections.emptyList());
        List<InquiryDTO> result = inquiryService.selectAllInquiries(Collections.singletonList(optionDTO));
        assertTrue(result.isEmpty());
        verify(inquiryMapper, times(1)).selectAllInquiries(anyList());
    }

    @Test
    void selectInquiry() {
        // 준비: InquiryVO 객체를 생성하고 예상되는 InquiryDTO 객체를 설정합니다.
        InquiryVO inquiryVO = InquiryVO.builder()
                .postNum(12345)
                .postType("Customer Service")
                .postTitle("Order Delivery Delay")
                .userId("user123")
                .postContent("My order was supposed to arrive last week, but I've not received it yet. Please advise.")
                .postDate(new Date()) // Assuming the inquiry was posted right now
                .response("Your inquiry has been received and is being reviewed by our support team.")
                .build();

        InquiryDTO expectedInquiryDTO = InquiryDTO.builder()
                .postNum(12345)
                .postType("Customer Service")
                .postTitle("Order Delivery Delay")
                .userId("user123")
                .postContent("My order was supposed to arrive last week, but I've not received it yet. Please advise.")
                .postDate(new Date()) // Assuming the inquiry was posted right now
                .response("Your inquiry has been received and is being reviewed by our support team.")
                .build();

        // 모의 설정: InquiryMapper와 ModelMapper의 동작을 모의합니다.
        given(inquiryMapper.selectInquiry(anyInt())).willReturn(inquiryVO);
        given(modelMapper.map(any(InquiryVO.class), eq(InquiryDTO.class))).willReturn(expectedInquiryDTO);

        // 실행: selectInquiry 메소드를 호출합니다.
        InquiryDTO result = inquiryService.selectInquiry(12345);

        // 검증: 결과가 null이 아니며, 예상되는 InquiryDTO 객체와 일치하는지 확인합니다.
        assertNotNull(result);
        assertEquals(expectedInquiryDTO.getPostNum(), result.getPostNum()); // 추가적인 필드 검증이 가능합니다.
        verify(inquiryMapper, times(1)).selectInquiry(12345);
        verify(modelMapper, times(1)).map(any(InquiryVO.class), eq(InquiryDTO.class));
    }


    @Test
    void deleteInquiry() {
        doNothing().when(inquiryMapper).deleteInquiry(anyInt());
        inquiryService.deleteInquiry(1);
        verify(inquiryMapper, times(1)).deleteInquiry(1);
    }

    @Test
    void updateInquiry() {
        doNothing().when(inquiryMapper).updateInquiry(any());
        inquiryService.updateInquiry(inquiryDTO);
        verify(inquiryMapper, times(1)).updateInquiry(any());
    }
}
