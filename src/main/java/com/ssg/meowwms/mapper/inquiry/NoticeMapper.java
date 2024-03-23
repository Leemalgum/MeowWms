package com.ssg.meowwms.mapper.inquiry;

import com.ssg.meowwms.domain.inquiry.NoticeVO;
import com.ssg.meowwms.dto.OptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {

    void insertNotice(NoticeVO notice);

    List<NoticeVO> selectAllNotices(@Param("options") List<OptionDTO> options);

    NoticeVO selectNotice(int no);

    void deleteNotice(int no);

    void updateNotice(NoticeVO notice);
}
