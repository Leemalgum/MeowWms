package com.ssg.meowwms.mapper.dispatch;

import java.util.List;
import com.ssg.meowwms.domain.dispatch.DispatchVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DispatchMapper {
    void insertdispatch(DispatchVO dispatchVO);
    DispatchVO selectOneById(int id);
    List<DispatchVO> selectAll();
    void updatedispatch(DispatchVO dispatchVO);
    void deletedispatch(int id);
}
