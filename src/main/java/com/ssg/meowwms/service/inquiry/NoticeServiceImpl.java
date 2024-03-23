package com.ssg.meowwms.service.inquiry;

import com.ssg.meowwms.domain.inquiry.NoticeVO;
import com.ssg.meowwms.dto.OptionDTO;
import com.ssg.meowwms.dto.inquiry.NoticeDTO;
import com.ssg.meowwms.mapper.inquiry.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper noticeMapper;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void insertNotice(NoticeDTO noticeDTO) {
        NoticeVO noticeVO = modelMapper.map(noticeDTO, NoticeVO.class);
        noticeMapper.insertNotice(noticeVO);
    }

    @Override
    public List<NoticeDTO> selectAllNotices(List<OptionDTO> options) {
        List<NoticeVO> notices = noticeMapper.selectAllNotices(options);
        return notices.stream()
                .map(notice -> modelMapper.map(notice, NoticeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public NoticeDTO selectNotice(int no) {
        NoticeVO notice = noticeMapper.selectNotice(no);
        return modelMapper.map(notice, NoticeDTO.class);
    }

    @Override
    @Transactional
    public void deleteNotice(int no) {
        noticeMapper.deleteNotice(no);
    }

    @Override
    @Transactional
    public void updateNotice(NoticeDTO noticeDTO) {
        NoticeVO noticeVO = modelMapper.map(noticeDTO, NoticeVO.class);
        noticeMapper.updateNotice(noticeVO);
    }
}
