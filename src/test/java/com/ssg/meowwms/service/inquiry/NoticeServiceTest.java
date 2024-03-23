package com.ssg.meowwms.service.inquiry;

import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.inquiry.NoticeDTO;
import com.ssg.meowwms.domain.inquiry.NoticeVO;
import com.ssg.meowwms.mapper.inquiry.NoticeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class NoticeServiceTest {

    @Mock
    private NoticeMapper noticeMapper;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private NoticeServiceImpl noticeService;

    private NoticeDTO noticeDTO;
    private NoticeVO noticeVO;

    @BeforeEach
    void setUp() {
        noticeDTO = NoticeDTO.builder()
                .no(1)
                .type("General")
                .title("Test Notice")
                .content("This is a test notice.")
                .userId("user123")
                .noticeDate(new Date())
                .build();

        noticeVO = NoticeVO.builder()
                .no(1)
                .type("General")
                .title("Test Notice")
                .content("This is a test notice.")
                .userId("user123")
                .noticeDate(new Date())
                .build();

        when(modelMapper.map(any(NoticeDTO.class), eq(NoticeVO.class))).thenReturn(noticeVO);
        when(modelMapper.map(any(NoticeVO.class), eq(NoticeDTO.class))).thenReturn(noticeDTO);
    }

    @Test
    void insertNotice() {
        noticeService.insertNotice(noticeDTO);
        verify(noticeMapper, times(1)).insertNotice(any(NoticeVO.class));
    }

    @Test
    void selectAllNotices() {
        when(noticeMapper.selectAllNotices(anyList())).thenReturn(Arrays.asList(noticeVO));
        List<NoticeDTO> results = noticeService.selectAllNotices(Arrays.asList(new OptionDTO()));

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        NoticeDTO result = results.get(0);
        assertEquals(noticeDTO.getNo(), result.getNo());
        assertEquals(noticeDTO.getTitle(), result.getTitle());
    }

    @Test
    void selectNotice() {
        when(noticeMapper.selectNotice(anyInt())).thenReturn(noticeVO);
        NoticeDTO result = noticeService.selectNotice(1);

        assertNotNull(result);
        assertEquals(noticeDTO.getNo(), result.getNo());
        assertEquals(noticeDTO.getTitle(), result.getTitle());
    }

    @Test
    void deleteNotice() {
        noticeService.deleteNotice(1);
        verify(noticeMapper, times(1)).deleteNotice(1);
    }

    @Test
    void updateNotice() {
        noticeService.updateNotice(noticeDTO);
        verify(noticeMapper, times(1)).updateNotice(any(NoticeVO.class));
    }
}
