package com.ssg.meowwms.mapper;

import com.ssg.meowwms.domain.WarehouseVO;

/**
 * 창고 테이블과의 연결 작업을 수행합니다.
 */
public interface WarehouseMapper {

    /**
     * WarehouseVO에 클라이언트에서 입력 받은 정보를 담아 창고 테이블에 입력합니다.
     *
     * @param warehouseVO
     */
    void insert(WarehouseVO warehouseVO);
}
