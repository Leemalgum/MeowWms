package com.ssg.meowwms.mapper.storage;

import com.ssg.meowwms.domain.storage.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {
    int productInsert(ProductVO product);
    void productUpdate(@Param("requestId") int requestId, @Param("product") ProductVO product);

}
