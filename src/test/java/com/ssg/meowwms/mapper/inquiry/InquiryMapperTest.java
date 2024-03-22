package com.ssg.meowwms.mapper.inquiry;

import com.ssg.meowwms.domain.inquiry.InquiryVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class InquiryMapperTest {

    @Autowired
    private InquiryMapper inquiryMapper;

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
    void selectAllInquiries() {
        List<InquiryVO> inquiries = inquiryMapper.selectAllInquiries();
    }

    @Test
    void selectInquiry() {
        InquiryVO inquiry = inquiryMapper.selectInquiry(11);
        System.out.println(inquiry);
    }

    @Test
    void deleteInquiry() {
        inquiryMapper.deleteInquiry(21);
    }

    @Test
    void updateInquiry() {
        InquiryVO inquiryToUpdate = InquiryVO.builder()
                .postNum(11)
                .postType("Updated Type")
                .postTitle("Updated Title")
                .postContent("Updated Content")
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
    }

}
