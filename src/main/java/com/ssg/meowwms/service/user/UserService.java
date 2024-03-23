package com.ssg.meowwms.service.user;

import com.ssg.meowwms.dto.user.UserDTO;

import java.util.List;

public interface UserService {
    void register(UserDTO userDTO);
    void modify(UserDTO userDTO);
    UserDTO getOne(String id);
    List<UserDTO> getList();
    UserDTO searchId(String name, String email);
    UserDTO searchPw(String name, String id);
}
