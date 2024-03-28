package com.ssg.meowwms.mapper.dispatch;

import java.util.List;
import com.ssg.meowwms.domain.dispatch.DispatchVO;
import com.ssg.meowwms.domain.retrieval.ShippingOrdersVO;
import com.ssg.meowwms.dto.search.OptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DispatchMapper {
    void insertDispatch(DispatchVO dispatchVO);
    DispatchVO selectOneByShippingOrdersId(int id);
    List<DispatchVO> selectAll();
    void updateByShippingOrdersId(DispatchVO dispatchVO);
    void deleteByShippingOrdersId(int id);
    List<ShippingOrdersVO> search(@Param("options") List<OptionDTO> options);
}
