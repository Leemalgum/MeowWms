package com.ssg.meowwms.mapper.storage;

import com.ssg.meowwms.domain.storage.QrcodeVO;
import org.apache.ibatis.annotations.Param;

public interface QrMapper {
    void insertQrCode(QrcodeVO qrcodeVO);
    void saveQrCodeImage(@Param("productId") int productId, @Param("filePath") String filePath);
}
