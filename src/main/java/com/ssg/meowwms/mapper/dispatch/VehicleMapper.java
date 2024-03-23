package com.ssg.meowwms.mapper.dispatch;

import java.util.List;
import com.ssg.meowwms.domain.dispatch.VehicleVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VehicleMapper {
    void insertVehicle(VehicleVO vehicleVO);
    VehicleVO selectOneByNum(String num);
    List<VehicleVO> selectAll();
    void updateVehicle(String num);
    void deleteVehicle(String num);
}
