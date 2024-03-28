package com.ssg.meowwms.controller.user;

import com.ssg.meowwms.dto.user.UserDTO;
import com.ssg.meowwms.security.SecurityUtils;
import com.ssg.meowwms.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 사용자 관련 기능을 처리하는 컨트롤러 입니다
 */
@Controller
@RequestMapping("/views/user")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;

    @GetMapping("/dashboard")
    public void main() {

    }

    /**
     * 로그인 페이지를 보여주고, 사용자의 로그일을 처리
     */
    @GetMapping("/login")
    public void loginGet() {
//        return "views/user/login";
    }

    /**
     * 로그아웃 기능을 처리
     *
     * @return
     */
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/views/user/login";
    }

    /**
     * 회원가입 페이지
     */
    @GetMapping("/register")
    public void registerGet() {

    }

    /**
     * 회원가입을 처리
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/register")
    public String registerPost(@ModelAttribute UserDTO userDTO) {
        userService.register(userDTO);
        log.info(userDTO);
        return "redirect:/views/user/login";
    }

    /**
     * 로그인한 사용자의 정보를 JSON 형식으로 반환
     */
    @GetMapping("/getUserData")
    @ResponseBody
    public UserDTO getUserData() {
        UserDTO userDTO = userService.getOne(SecurityUtils.getCurrentUserDetails().getUsername()).orElse(null);
        // 가져온 사용자 정보를 JSON 형식으로 응답합니다.
        log.info(userDTO);
        return userDTO;
    }

    @GetMapping("/myInfo")
    public void getOne() {
//        return "views/user/myInfo";
    }

    /**
     * 회원 목록 페이지
     */
    @GetMapping("/memberList")
    public void getList() {

    }

    /**
     * 전체 회원 데이터를 가져오는 API
     *
     * @return
     */
    @GetMapping("/data")
    @ResponseBody
    public List<UserDTO> getUserDataList() {
        List<UserDTO> list = userService.getList();
//        list.forEach(log::info);
        return list;
    }

    /**
     * 이메일 변경을 처리
     *
     * @param newEmail
     */
    @PostMapping("/change-email")
    @ResponseBody
    public void changeEmail(@RequestParam("newEmail") String newEmail) {
        UserDTO userDTO = userService.getOne(SecurityUtils.getCurrentUserDetails().getUsername()).orElse(null);
        userDTO.setEmail(newEmail);
        userService.modify(userDTO);
    }

    /**
     * 전화번호 변경을 처리
     *
     * @param newTel
     */
    @PostMapping("/change-tel")
    @ResponseBody
    public void changeTel(@RequestParam("newTel") String newTel) {
        UserDTO userDTO = userService.getOne(SecurityUtils.getCurrentUserDetails().getUsername()).orElse(null);
        userDTO.setTel(newTel);
        userService.modify(userDTO);
    }

    /**
     * 강제 탈퇴를 처리
     */
    @PostMapping("/withdraw")
    @ResponseBody
    public void withdrawUser() {
        // 현재 사용자의 정보를 가져와서 탈퇴 처리를 진행합니다.
        UserDTO userDTO = userService.getOne(SecurityUtils.getCurrentUserDetails().getUsername()).orElse(null);
        // 회원 상태를 Inactive로 변경합니다.
        userDTO.setSid(2);
        userService.modify(userDTO);
    }

    /**
     * 사용자 정보를 저장하고 수정을 처리
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody UserDTO userDTO) {
        log.info(userDTO);
        UserDTO userSave = userService.getOne(userDTO.getUid()).orElse(null);
        log.info(userSave);
        if (userSave != null) {
            userSave.setRid(userDTO.getRid());
            userSave.setSid(userDTO.getSid());
            userSave.setTel(userDTO.getTel());
            userSave.setEmail(userDTO.getEmail());
            userService.modify(userSave);
            return ResponseEntity.ok("User saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    /**
     * 사용자 강제 탈퇴를 처리
     *
     * @param userId
     * @return
     */
    @PostMapping("/forcedWithdrawal")
    @ResponseBody
    public String forcedWithdrawal(@RequestParam("userId") String userId) {
        // 여기서 userId 값을 사용하여 해당 유저를 강제탈퇴 처리하거나 필요한 작업 수행
        UserDTO userDTO = userService.getOne(userId).orElse(null);
        userDTO.setSid(3);
        userService.modify(userDTO);
        // 처리 결과를 클라이언트에게 응답
        return "User ID: " + userId + " forced withdrawal completed";
    }

    /**
     * 사용자 아이디 찾기를 처리
     *
     * @param name
     * @param email
     * @return
     */
    @PostMapping("/findId")
    @ResponseBody //JSON 형식으로 응답
    public String findUserId(@RequestParam("name") String name,
                             @RequestParam("email") String email) {
        return userService.searchId(name, email);
    }

}
