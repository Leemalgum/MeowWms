package com.ssg.meowwms.mapper.User;

import com.ssg.meowwms.domain.User.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface UserMapper {

    void insertUser(UserVO userVO);

    void updateStatus(UserVO userVO);

    UserVO selectUser(String uid);

    List<UserVO> selectAll();
    List<UserVO> selectSearch(int rid);

    String searchId(String uname, String email);

    String searchPw(String uname, String uid);
}
