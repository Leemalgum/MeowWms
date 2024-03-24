package com.ssg.meowwms.mapper.category;

import com.ssg.meowwms.domain.category.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 카테고리 테이블과의 연결 작업을 수행합니다.
 */
@Mapper
public interface CategoryMapper {

    /**
     * 카테고리 테이블에서 모든 정보를 가져와 반환합니다.
     *
     * @return
     */
    List<CategoryVO> selectAll();
}
