package com.ssg.meowwms.mapper.dispatch;

import java.util.List;
import com.ssg.meowwms.domain.dispatch.VehicleVO;
import com.ssg.meowwms.domain.retrieval.ShippingOrdersVO;
import com.ssg.meowwms.dto.OptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VehicleMapper {
    void insertVehicle(VehicleVO vehicleVO);
    VehicleVO selectOneByNum(String num);
    List<VehicleVO> selectAll(@Param("options") List<OptionDTO> options);
    void updateVehicle(String num);
    void deleteVehicle(String num);
    List<ShippingOrdersVO> search(@Param("options") List<OptionDTO> options);
}
