package com.ssg.meowwms.mapper.retrieval;

import com.ssg.meowwms.domain.retrieval.ShippingOrdersVO;
import com.ssg.meowwms.domain.retrieval.WaybillVO;
import com.ssg.meowwms.dto.OptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WaybillMapper {
    void insertWaybill(WaybillVO waybillVO);
    WaybillVO selectOneById(int id);
    List<WaybillVO> selectAll(@Param("options") List<OptionDTO> options);
    void updateWaybill(WaybillVO waybillVO);
    void deleteWaybill(int id);
    List<ShippingOrdersVO> search(@Param("options") List<OptionDTO> options);
}
