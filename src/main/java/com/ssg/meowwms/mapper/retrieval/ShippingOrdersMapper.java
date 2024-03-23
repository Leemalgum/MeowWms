package com.ssg.meowwms.mapper.retrieval;

import com.ssg.meowwms.domain.retrieval.ShippingOrdersVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShippingOrdersMapper {
    void insertShippingOrder(ShippingOrdersVO shippingOrdersVO);
    ShippingOrdersVO selectOneById(int id);
    List<ShippingOrdersVO> selectAll();
    void deleteShippingOrder(int id);
}
