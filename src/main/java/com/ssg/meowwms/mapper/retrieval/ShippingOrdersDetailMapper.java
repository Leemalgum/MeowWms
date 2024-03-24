package com.ssg.meowwms.mapper.retrieval;

import com.ssg.meowwms.domain.retrieval.ShippingOrdersDetailVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShippingOrdersDetailMapper {
    void insertShippingOrderDetail(ShippingOrdersDetailVO shippingOrdersDetailVO);
    ShippingOrdersDetailVO selectOnelById(String id);
    void updateShippingOrderDetail(ShippingOrdersDetailVO shippingOrdersDetailVO);
    void deleteShippingOrderDetail(String id);
}
