package com.ssg.meowwms.mapper.user;

import com.ssg.meowwms.domain.user.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 사용자 정보를 데이터베이스에서 조회, 삽입, 업데이트하는 데 사용되는 매퍼 인터페이스 입니다
 */
@Mapper
public interface UserMapper {

    /**
     * UserVO 객체를 이용해 데이터베이스에 사용자를 삽입하는 메서드 입니다
     * @param userVO
     */
    void insert(UserVO userVO);

    /**
     * UserVO 객체를 이용해 데이터베이스에 사용자의 정보를 수정하는 메서드 입니다
     * @param userVO
     */
    void update(UserVO userVO);

    /**
     * uid를 이용해 데이터베이스에서 사용자 정보를 조회하는 메서드 입니다
     * @param uid
     * @return UserVO
     */
    UserVO selectUser(String uid);

    /**
     * 데이터베이스에서 전체 사용자를 조회하는 메서드 입니다
     * @return List<UserVO>
     */
    List<UserVO> selectAll();

    /**
     * 입력받은 이름과 이메일을 통해 데이터베이스에서 사용자의 아이디를 조회하는 메서드 입니다
     * @param uname
     * @param email
     * @return String uid
     */
    String searchId(@Param("uname") String uname, @Param("email") String email);

    String searchPw(@Param("uname") String uname, @Param("uid") String uid);

    /**
     * User 테이블에서 창고 관리자만 조회합니다.
     *
     * @return 창고 관리자 목록
     */
    List<UserVO> selectWarehouseManager();

    int totalUserCount();
    int nonUserRequest();
}
