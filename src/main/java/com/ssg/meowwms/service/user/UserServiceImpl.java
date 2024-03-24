package com.ssg.meowwms.service.user;

import com.ssg.meowwms.domain.user.UserVO;
import com.ssg.meowwms.dto.user.UserDTO;
import com.ssg.meowwms.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    @Override
    public void register(UserDTO userDTO) {
        String mpw = userDTO.getUpw();
        userDTO.setUpw(passwordEncoder.encode(mpw));
        UserVO userVO = modelMapper.map(userDTO, UserVO.class);
        userMapper.insert(userVO);
    }

    @Override
    public void modify(UserDTO userDTO) {
        UserVO userVO = modelMapper.map(userDTO, UserVO.class);
        userMapper.update(userVO);

    }

    @Override
    public Optional<UserDTO> getOne(String id) {
        UserVO userVO = userMapper.selectUser(id);
        if(userVO == null){
            return Optional.empty();
        }
        UserDTO userDTO = modelMapper.map(userVO, UserDTO.class);
        return Optional.ofNullable(userDTO);
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
