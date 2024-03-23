package com.ssg.meowwms.mapper.retrieval;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShippingOrdersStatusMapper {
    void updateAllocatedStatus(int id);
    void updateApprovedstatus(int id);
}
