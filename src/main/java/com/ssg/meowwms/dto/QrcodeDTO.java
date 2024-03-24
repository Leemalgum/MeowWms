package com.ssg.meowwms.dto;

import lombok.*;

import java.sql.Blob;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QrcodeDTO {
    private int id;
    private int productId;
    private Blob barcodeData;
    private Date creationDate;
}
