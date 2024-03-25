package com.ssg.meowwms.mapper.user;

import com.ssg.meowwms.domain.user.UserVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@SpringBootTest
@Transactional
class UserMapperTest {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Test
    void insertUser() {
        UserVO userVO = UserVO.builder()
                .uid("makeum3")
                .uname("이맑음")
                .birth(LocalDate.parse("1998-12-25"))
                .upw("makeum")
                .email("makeum@test.com")
                .tel("01011112222")
                .build();
        userMapper.insert(userVO);
        log.info("----userVO-----" + userVO);
    }


    @Test
    void updateStatus() {
        UserVO userVO = UserVO.builder()
                .uid("makeum")
                .sid(1)
                .rid(3)
                .upw("updatePw")
                .tel("01011114444")
                .email("updateEmail@test.com")
                .build();
        userMapper.update(userVO);
    }

    @Test
    void selectUser() {
        String uid = "user2";
        UserVO userVO = userMapper.selectUser(uid);
        log.info(userVO);
    }

    @Test
    void selectAll() {
        List<UserVO> userVOList = userMapper.selectAll();
        userVOList.forEach(log::info);
    }

//    @Test
//    void selectSearch() {
//        int rid = 2;
//        List<UserVO> userVOList = userMapper.selectSearch(null);
//        userVOList.forEach(log::info);
//    }

    @Test
    void searchId() {
        String uname = "user2";
        String email = "user2@example.com";
        String uid = userMapper.searchId(uname, email);
        log.info(uid);
    }

    @Test
    void searchPw() {
        String uname = "이맑음";
        String uid = "makeum";
        String upw = userMapper.searchPw(uname, uid);
        log.info(upw);
    }

    @Test
    @DisplayName("창고 관리자 조회")
    void selectWarehouseManager() {
        List<UserVO> warehouseManagerList = userMapper.selectWarehouseManager();

        log.info(warehouseManagerList);
    }
}