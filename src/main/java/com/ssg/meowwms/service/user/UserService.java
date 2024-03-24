package com.ssg.meowwms.service.user;

import com.ssg.meowwms.dto.user.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void register(UserDTO userDTO);
    void modify(UserDTO userDTO);
    Optional<UserDTO> getOne(String id);
    List<UserDTO> getList();
    String searchId(String uname, String email);
    String searchPw(String uname, String id);
}
