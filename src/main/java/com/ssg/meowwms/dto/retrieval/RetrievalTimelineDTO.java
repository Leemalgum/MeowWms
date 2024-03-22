package com.ssg.meowwms.dto.retrieval;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetrievalTimelineDTO {
    private int id;
    private LocalDateTime requestTime;
    private LocalDateTime orderTime;
    private LocalDateTime workingTime;
    private LocalDateTime finishedTime;

}
