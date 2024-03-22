package com.ssg.meowwms.mapper.inquiry;

import com.ssg.meowwms.domain.inquiry.NoticeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    void insertNotice(NoticeVO notice);

    List<NoticeVO> selectAllNotices();

    NoticeVO selectNotice(int no);

    void deleteNotice(int no);

    void updateNotice(NoticeVO notice);
}
