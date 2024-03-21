package com.ssg.meowwms.mapper;

import com.ssg.meowwms.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<UserVO> selectById(String id);
}
