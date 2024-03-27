package com.ssg.meowwms.service.user;

import com.ssg.meowwms.dto.user.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 * 사용자 서비스에 관련된 기능을 정의하는 인터페이스 입니다
 */
public interface UserService {
    /**
     * 사용자 등록
     * @param userDTO
     */
    void register(UserDTO userDTO);

    /**
     * 사용자 정보 수정
     * @param userDTO
     */
    void modify(UserDTO userDTO);

    /**
     * id를 통해 해당하는 사용자 정보를 조회
     * @param id
     * @return
     */
    Optional<UserDTO> getOne(String id);

    /**
     * 전체 사용자 정보를 리스트 형태로 조회
     * @return
     */
    List<UserDTO> getList();

    /**
     * 사용자 이름과 이메일을 통해 사용자 아이디를 조회
     * @param uname
     * @param email
     * @return
     */
    String searchId(String uname, String email);

    /**
     * 사용자 이름과 아이디를 통해 사용자 비밀번호를 조회
     * @param uname
     * @param id
     * @return
     */
    String searchPw(String uname, String id);
    List<UserDTO> getWarehouseManager();

    /**
     * 전체 회원 수를 조회
     * @return
     */
    int totalUserCount();

    /**
     * 회원 가입 요청 수 조회
     * @return
     */
    int nonUserRequest();
}
