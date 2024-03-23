package com.ssg.meowwms.mapper.dispatch;

import java.util.List;
import com.ssg.meowwms.domain.dispatch.DispatchVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DispatchMapper {
    void insertDispatch(DispatchVO dispatchVO);
    DispatchVO selectOneById(int id);
    List<DispatchVO> selectAll();
    void updateDispatch(DispatchVO dispatchVO);
    void deleteDispatch(int id);
}
