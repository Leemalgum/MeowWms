package com.ssg.meowwms.service.user;

import com.ssg.meowwms.domain.user.UserVO;
import com.ssg.meowwms.dto.user.UserDTO;
import com.ssg.meowwms.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final ModelMapper modelMapper;
    @Override
    public void register(UserDTO userDTO) {
        UserVO userVO = modelMapper.map(userDTO, UserVO.class);
        userMapper.insert(userVO);
    }

    @Override
    public void modify(UserDTO userDTO) {
        UserVO userVO = modelMapper.map(userDTO, UserVO.class);
        userMapper.update(userVO);

    }

    @Override
    public UserDTO getOne(String id) {
        return null;
    }

    @Override
    public List<UserDTO> getList() {
        return null;
    }

    @Override
    public UserDTO searchId(String name, String email) {
        return null;
    }

    @Override
    public UserDTO searchPw(String name, String id) {
        return null;
    }
}
