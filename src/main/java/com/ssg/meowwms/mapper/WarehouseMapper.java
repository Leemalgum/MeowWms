package com.ssg.meowwms.mapper;

import com.ssg.meowwms.domain.warehouse.WarehouseVO;

import com.ssg.meowwms.dto.search.OptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 창고 테이블과의 연결 작업을 수행합니다.
 */
@Mapper
public interface WarehouseMapper {

    /**
     * WarehouseVO에 클라이언트에서 입력 받은 정보를 담아 창고 테이블에 입력합니다.
     *
     * @param warehouseVO
     */
    void insert(WarehouseVO warehouseVO);

    /**
     * 창고 테이블에서 warehouseId에 해당하는 창고 정보를 가져와 WarehouseVO로 반환합니다.
     *
     * @param warehouseId
     * @return
     */
    WarehouseVO selectOne(int warehouseId);

    /**
     * 창고 테이블에서 전체 창고에 대한 정보를 가져와서 반환합니다.
     * 검색 조건이 있을 경우 해당 검색 조건에 해당하는 창고들만 가져와서 반환합니다.
     *
     * @param optionList
     * @return
     */
    List<WarehouseVO> selectAll(@Param("optionList")List<OptionDTO> optionList);
}
