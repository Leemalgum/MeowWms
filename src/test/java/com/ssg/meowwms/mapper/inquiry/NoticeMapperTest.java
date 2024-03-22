package com.ssg.meowwms.mapper.inquiry;

import com.ssg.meowwms.domain.inquiry.NoticeVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
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
    void selectAllNotices() {
        // 데이터 삽입 후 전체 조회
        List<NoticeVO> notices = noticeMapper.selectAllNotices();
        assertThat(notices).isNotNull();
        System.out.println(notices);
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
