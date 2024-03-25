package com.ssg.meowwms.mapper.storage;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

@Mapper
public interface QrMapper {
    void saveQrCodeImage(@Param("productId") int productId, @Param("qrCodeImage") byte[] qrCodeImage, @Param("creationDate") LocalDate creationDate);
}
