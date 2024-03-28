package com.ssg.meowwms.mapper.storage;


import com.ssg.meowwms.domain.storage.QrcodeVO;
import com.ssg.meowwms.domain.storage.StockMovementVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface QrMapper {
    void saveQrCodeImage(@Param("productId") int productId, @Param("qrCodeImage") byte[] qrCodeImage, @Param("creationDate") LocalDate creationDate);

    @Select("SELECT barcode_data FROM QR_Barcode WHERE product_id = #{productId}")
    byte[] getQrCodeImageByProductId(@Param("productId") int productId);
}