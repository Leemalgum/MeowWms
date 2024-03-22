package com.ssg.meowwms.mapper.inquiry;

import com.ssg.meowwms.domain.inquiry.NoticeVO;
import com.ssg.meowwms.dto.OptionDTO;
import lombok.extern.log4j.Log4j2;
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
public class NoticeMapperTest {

    @Autowired
    private NoticeMapper noticeMapper;

    @Test
    void insertNotice() {
        NoticeVO notice = NoticeVO.builder()
                .userId("admin")
                .type("General")
                .title("Test Notice")
                .content("This is a test notice.")
                .build();

        noticeMapper.insertNotice(notice);
        // 삽입 후 조회하여 검증
    }

    @Test
    public void selectAllNoticesTest() {
        // 검색 조건 예시: 타입으로 검색
        List<OptionDTO> options = new ArrayList<>();
        options.add(new OptionDTO("postType", "공지")); // "type"이 "공지"인 공지사항 검색
        options.add(new OptionDTO("title", "변경")); // "title"에 "제목"이 포함된 공지사항 검색

        // 검색 실행
        List<NoticeVO> results = noticeMapper.selectAllNotices(options);

        // 검증: 결과가 비어 있지 않은지 확인 (실제 데이터와 테스트 환경에 따라 달라질 수 있음)
        assertThat(results).isNotEmpty();

        // 추가 검증: 반환된 객체의 내용 확인, 필요한 경우 더 구체적인 검증 수행
        results.forEach(notice -> {
            log.info(notice);
            assertThat(notice.getTitle()).contains("변경");
        });
    }

    @Test
    void selectNotice() {
        int no = 12; // 가정된 공지사항 번호
        NoticeVO notice = noticeMapper.selectNotice(no);
        assertThat(notice).isNotNull();
    }

    @Test
    void deleteNotice() {
        int no = 12; // 가정된 공지사항 번호
        noticeMapper.deleteNotice(no);
        assertThat(noticeMapper.selectNotice(12)).isNull();
        // 삭제 후 검증
    }

    @Test
    void updateNotice() {
        NoticeVO notice = NoticeVO.builder()
                .no(12) // 가정된 공지사항 번호
                .userId("admin")
                .type("Update")
                .title("Updated Notice")
                .content("This is an updated test notice.")
                .build();

        noticeMapper.updateNotice(notice);
        NoticeVO updateNotice = noticeMapper.selectNotice(12);
        assertThat(updateNotice.getContent()).isEqualTo("This is an updated test notice.");
        // 업데이트 후 검증
    }
}
