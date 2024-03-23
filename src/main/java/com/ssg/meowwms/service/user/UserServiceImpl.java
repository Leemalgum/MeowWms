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
        UserVO userVO = userMapper.selectUser(id);
        return modelMapper.map(userVO, UserDTO.class);
    }

    @Override
    public List<UserDTO> getList() {
        List<UserDTO> userDTOList = userMapper.selectAll()
                .stream()
                .map(userVO -> modelMapper.map(userVO, UserDTO.class))
                .toList();
        return userDTOList;
    }

    @Override
    public String searchId(String uname, String email) {
        String uid = userMapper.searchId(uname, email);
        return uid;
    }

    @Override
    public String searchPw(String uname, String id) {
        String upw = userMapper.searchId(uname, id);
        return upw;
    }
}
