package com.ssg.meowwms.mapper.storage;

import com.ssg.meowwms.domain.storage.ProductVO;
import com.ssg.meowwms.domain.storage.StockMovementVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    int productInsert(ProductVO product);
    void productUpdate(@Param("requestId") int requestId, @Param("product") ProductVO product);
    List<ProductVO> selectAllProducts();
    List<ProductVO> searchProducts(@Param("searchKeyword") String searchKeyword);
    int selectSumOfVolume();
}
