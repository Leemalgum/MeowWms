package com.ssg.meowwms.mapper.warehouse;

import com.ssg.meowwms.domain.warehouse.WarehouseVO;
import com.ssg.meowwms.dto.search.OptionDTO;
import com.ssg.meowwms.dto.warehouse.WarehouseDTO;
import com.ssg.meowwms.dto.warehouse.WarehouseDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

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

    /**
     * 파라미터로 주어진 창고 아이디에 해당하는 창고의 상세정보를 반환합니다.
     *
     * @param warehouseId
     * @return
     */
    List<WarehouseDetailDTO> selectWarehouseDetail(int warehouseId);

    /**
     * 주어진 카테고리 아이디에 해당하는 창고 목록을 반환합니다.
     *
     * @param categoryId
     * @return
     */
    List<WarehouseDTO> selectWarehouseWithCategory(int categoryId);

    /**
     * 창고 이름을 전달 받아 해당하는 창고의 아이디를 반환
     *
     * @param warehouseName
     * @return 창고 아이디
     */
    Optional<Integer> selectIdByName(String warehouseName);
}
