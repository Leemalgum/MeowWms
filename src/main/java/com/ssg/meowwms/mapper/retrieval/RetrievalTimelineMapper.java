package com.ssg.meowwms.mapper.retrieval;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RetrievalTimelineMapper {
    void insertRequestTime(int id);
    void insertOrderTime(int id);
    void insertWorkingTime(int id);
    void insertFinishedTime(int id);
}
