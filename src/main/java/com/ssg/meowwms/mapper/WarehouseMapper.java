package com.ssg.meowwms.mapper;

import com.ssg.meowwms.domain.WarehouseVO;
import org.apache.ibatis.annotations.Mapper;

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
}
