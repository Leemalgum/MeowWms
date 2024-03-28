package com.ssg.meowwms.mapper.retrieval;

import com.ssg.meowwms.domain.retrieval.ShippingOrdersDetailVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShippingOrdersDetailMapper {
    void insertShippingOrderDetail(ShippingOrdersDetailVO shippingOrdersDetailVO);
    ShippingOrdersDetailVO selectOneByShippingOrderId(int shippingOrdersId);
    void updateByShippingOrderId(ShippingOrdersDetailVO shippingOrdersDetailVO);
    void deleteShippingOrderDetailById(int id);
}
