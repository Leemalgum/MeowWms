package com.ssg.meowwms.mapper.retrieval;

import com.ssg.meowwms.domain.retrieval.WaybillVO;
import com.ssg.meowwms.dto.retrieval.WaybillDTO;
import com.ssg.meowwms.dto.search.OptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WaybillMapper {
    void insertWaybill(WaybillVO waybillVO);
    WaybillVO selectOneByShippingOrderId(int shippingOrdersId);
    List<WaybillVO> selectByOptions(@Param("option") OptionDTO option);
    void updateByShippingOrderId(WaybillVO waybillVO);
    void deleteByShippingOrderId(int shippingOrdersId);
    WaybillDTO selectForFillUpWaybill(int shippingOrdersId);
}
