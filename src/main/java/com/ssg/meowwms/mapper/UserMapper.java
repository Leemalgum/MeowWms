package com.ssg.meowwms.mapper;

import com.ssg.meowwms.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    void insertUser(UserVO userVO);

    void updateStatus(UserVO userVO);

    UserVO selectUser(String uid);

    List<UserVO> selectAll();

    UserVO searchId(String uname, String email);

    UserVO searchPw(String uname, String uid);
}
