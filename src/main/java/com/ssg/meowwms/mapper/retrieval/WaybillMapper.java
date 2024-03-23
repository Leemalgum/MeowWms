package com.ssg.meowwms.mapper.retrieval;

import com.ssg.meowwms.domain.retrieval.WaybillVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WaybillMapper {
    void insertWaybill(WaybillVO waybillVO);
    WaybillVO selectOneById(int id);
    List<WaybillVO> selectAll();
    void updateWaybill(WaybillVO waybillVO);
    void deleteWaybill(int id);
}
