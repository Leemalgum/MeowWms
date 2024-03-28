package com.ssg.meowwms.mapper.inquiry;

import com.ssg.meowwms.domain.inquiry.InquiryVO;
import com.ssg.meowwms.dto.search.OptionDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Log4j2
class InquiryMapperTest {

    @Autowired
    private InquiryMapper inquiryMapper;
    private List<OptionDTO> options;

    @BeforeEach
    void setUp() {
        // Initialize your test conditions here
        options = new ArrayList<>();
        // Example option, adjust according to your actual test needs
        options.add(new OptionDTO("postType", "질문"));
        options.add(new OptionDTO("writer", "admin"));
    }

    @Test
    void selectAllInquiriesTest() {

        List<InquiryVO> results = inquiryMapper.selectAllInquiries(options);

        assertThat(results).isNotNull();
        assertThat(results.size()).isGreaterThan(0);

        for (InquiryVO inquiry : results) {
            assertThat(inquiry.getPostType()).isEqualTo("질문");
            assertThat(inquiry.getUserId()).isEqualTo("admin");
        }
    }

    @Test
    void insertInquiry() {
        InquiryVO inquiry = InquiryVO.builder()
                .postType("Type")
                .postTitle("Title")
                .userId("admin")
                .postContent("Content")
                .build();
        inquiryMapper.insertInquiry(inquiry);
    }


    @Test
    void selectInquiry() {
        InquiryVO inquiry = inquiryMapper.selectInquiry(11);
        log.info(inquiry);
        assertThat(inquiry.getUserId()).isEqualTo("admin");
    }

    @Test
    void deleteInquiry() {
        inquiryMapper.deleteInquiry(21);
        assertThat(inquiryMapper.selectInquiry(21)).isNull();
    }

    @Test
    void updateInquiry() {
        InquiryVO inquiryToUpdate = InquiryVO.builder()
                .postNum(11)
                .postType("Updated Type")
                .postTitle("Updated Title")
                .postContent("Updated Content")
                .response("응???")
                .build();

        // 업데이트 메서드 실행
        inquiryMapper.updateInquiry(inquiryToUpdate);

        // 업데이트된 객체 조회
        InquiryVO updatedInquiry = inquiryMapper.selectInquiry(11);
        System.out.println(updatedInquiry);

        // 검증
        assertThat(updatedInquiry.getPostType()).isEqualTo("Updated Type");
        assertThat(updatedInquiry.getPostTitle()).isEqualTo("Updated Title");
        assertThat(updatedInquiry.getPostContent()).isEqualTo("Updated Content");
        assertThat(updatedInquiry.getResponse()).isEqualTo("응???");
    }

    @Test
    public void selectDoNotResponseInquiry(){
        System.out.println((inquiryMapper.selectDoNotResponseInquiry()));
    }

}
