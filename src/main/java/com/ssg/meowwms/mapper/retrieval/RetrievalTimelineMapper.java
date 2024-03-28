package com.ssg.meowwms.mapper.retrieval;

import com.ssg.meowwms.domain.retrieval.RetrievalTimelineVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RetrievalTimelineMapper {
    void insertRequestTime(RetrievalTimelineVO retrievalTimelineVO);
    void updateOrderTime(RetrievalTimelineVO retrievalTimelineVO);
    void updateWorkingTime(RetrievalTimelineVO retrievalTimelineVO);
    void updateFinishedTime(RetrievalTimelineVO retrievalTimelineVO);
    void deleteByShippingOrdersId(int shippingOrdersId);
}
