package com.ssg.meowwms.mapper.retrieval;

import com.ssg.meowwms.MeowWmsApplication;
import com.ssg.meowwms.domain.retrieval.RetrievalTimelineVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
//@Transactional
public class RetrievalTimelineMapperTests {
    @Autowired
    private RetrievalTimelineMapper retrievalTimelineMapper;

    @Test
    void insertRetrievalTimelineTest() {
        RetrievalTimelineVO retrievalTimelineVO = RetrievalTimelineVO.builder()
                .id(5)
                .requestTime(LocalDateTime.now())
                .build();
        retrievalTimelineMapper.insertRequestTime(retrievalTimelineVO);
    }

    @Test
    void updateOrderTimeTest() {
        RetrievalTimelineVO retrievalTimelineVO = RetrievalTimelineVO.builder()
                .id(2)
                .orderTime(LocalDateTime.of(2024, 3, 25, 16, 15, 26))
                .build();
        retrievalTimelineMapper.updateOrderTime(retrievalTimelineVO);
    }

    @Test
    void updateWorkingTimeTest() {
        RetrievalTimelineVO retrievalTimelineVO = RetrievalTimelineVO.builder()
                .id(2)
                .workingTime(LocalDateTime.of(2024, 3, 25, 16, 40, 36))
                .build();
        retrievalTimelineMapper.updateWorkingTime(retrievalTimelineVO);
    }

    @Test
    void updateFinishedTimeTest() {
        RetrievalTimelineVO retrievalTimelineVO = RetrievalTimelineVO.builder()
                .id(2)
                .finishedTime(LocalDateTime.of(2024, 3, 25, 17, 05, 18))
                .build();
        retrievalTimelineMapper.updateFinishedTime(retrievalTimelineVO);
    }


}
