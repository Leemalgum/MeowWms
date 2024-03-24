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
    private String vehicle_num;

    @NotNull
    private String warehouse_id;

    @NotNull
    private int vehicle_status;

    @NotNull
    private LocalDateTime request_date;

    @Nullable
    private LocalDateTime dispatch_date;
}
