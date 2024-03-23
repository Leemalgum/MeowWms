package com.ssg.meowwms.mapper.retrieval;

import com.ssg.meowwms.domain.retrieval.ShippingOrdersVO;
import com.ssg.meowwms.dto.search.OptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShippingOrdersMapper {
    void insertShippingOrder(ShippingOrdersVO shippingOrdersVO);
    ShippingOrdersVO selectOneById(int id);
    List<ShippingOrdersVO> selectAll();
    void updateShippingOrder(ShippingOrdersVO shippingOrdersVO);
    void deleteShippingOrder(int id);
    List<ShippingOrdersVO> search(@Param("options") List<OptionDTO> options);

}
