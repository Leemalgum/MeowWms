package com.ssg.meowwms.mapper.dispatch;

import java.util.List;
import com.ssg.meowwms.domain.dispatch.DispatchVO;
import com.ssg.meowwms.domain.retrieval.ShippingOrdersVO;
import com.ssg.meowwms.dto.OptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DispatchMapper {
    void insertDispatch(DispatchVO dispatchVO);
    DispatchVO selectOneById(int id);
    List<DispatchVO> selectAll(@Param("options") List<OptionDTO> options);
    void updateDispatch(DispatchVO dispatchVO);
    void deleteDispatch(int id);
    List<ShippingOrdersVO> search(@Param("options") List<OptionDTO> options);
}
