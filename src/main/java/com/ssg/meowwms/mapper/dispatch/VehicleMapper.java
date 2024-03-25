package com.ssg.meowwms.mapper.dispatch;

import java.util.List;
import com.ssg.meowwms.domain.dispatch.VehicleVO;
import com.ssg.meowwms.dto.search.OptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VehicleMapper {
    void insertVehicle(VehicleVO vehicleVO);
    VehicleVO selectOneByNum(String num);
    void updateVehicle(String num);
    void deleteVehicle(String num);
    List<VehicleVO> selectByOptions(@Param("options") List<OptionDTO> options);
}
