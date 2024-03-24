package com.ssg.meowwms.mapper.storage;

import com.ssg.meowwms.domain.storage.ProductVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    int productInsert(ProductVO product);
    int productUpdate(int productId, ProductVO product);

}
