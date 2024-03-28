package com.ssg.meowwms.domain.dispatch;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DispatchVO {
    @NotNull
    private int id;

    @NotNull
    private String vehicleNum;

    @Nullable
    private int warehouseId;

    @NotNull
    private LocalDateTime requestDate;

    @Nullable
    private LocalDateTime dispatchDate;
}
