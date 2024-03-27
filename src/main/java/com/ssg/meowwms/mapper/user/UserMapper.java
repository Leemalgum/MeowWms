package com.ssg.meowwms.mapper.user;

import com.ssg.meowwms.domain.user.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    void insert(UserVO userVO);

    void update(UserVO userVO);

    UserVO selectUser(String uid);

    List<UserVO> selectAll();
    List<UserVO> selectSearch(int rid);

    String searchId(@Param("uname") String uname, @Param("email") String email);

    String searchPw(@Param("uname") String uname, @Param("uid") String uid);

    /**
     * User 테이블에서 창고 관리자만 조회합니다.
     *
     * @return 창고 관리자 목록
     */
    List<UserVO> selectWarehouseManager();
}
