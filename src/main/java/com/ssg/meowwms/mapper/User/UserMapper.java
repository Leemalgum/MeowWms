package com.ssg.meowwms.mapper.user;

import com.ssg.meowwms.domain.user.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void insert(UserVO userVO);

    void update(UserVO userVO);

    UserVO selectUser(String uid);

    List<UserVO> selectAll();
    List<UserVO> selectSearch(int rid);

    String searchId(String uname, String email);

    String searchPw(String uname, String uid);
}
