package com.ssg.meowwms.service.user;

import com.ssg.meowwms.dto.user.UserDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@SpringBootTest
@Transactional
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    void register() {
        UserDTO userDTO = UserDTO.builder()
                .uid("makeum4")
                .uname("이맑음")
                .birth(LocalDate.parse("1998-12-25"))
                .upw("makeum")
                .email("makeum@test.com")
                .tel("01011112222")
                .build();
        userService.register(userDTO);
        log.info("----userDTO-----" + userDTO);
    }

    @Test
    void modify() {
        UserDTO userDTO = UserDTO.builder()
                .uid("makeum")
                .uname("맑음쓰테스트")
                .birth(LocalDate.parse("1998-12-25"))
                .upw("makeum")
                .sid(1)
                .rid(2)
                .email("makeum@test.com")
                .tel("01011112222")
                .build();
        userService.modify(userDTO);
        log.info("----userDTO-----" + userDTO);
    }

    @Test
    void getOne() {
        UserDTO userDTO = userService.getOne("makeum");
        log.info(userDTO);
    }

    @Test
    void getList() {
        List<UserDTO> userDTOList = userService.getList();
        userDTOList.forEach(log::info);
    }

    @Test
    void searchId() {
        String uid = userService.searchId("user2", "user2@example.com");
        log.info(uid);
    }

    @Test
    void searchPw() {
        String upw = userService.searchPw("user2","user2");
        log.info(upw);
    }
}