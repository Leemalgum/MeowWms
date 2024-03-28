package com.ssg.meowwms.domain.storage;

import lombok.*;

import java.sql.Blob;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QrcodeVO {
    private int id;
    private int productId;
    private Blob barcodeData;
    private Date creationDate;
}
