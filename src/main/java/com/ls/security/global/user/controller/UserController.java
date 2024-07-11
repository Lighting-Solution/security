package com.ls.security.global.user.controller;

import com.ls.security.global.user.dto.AndroidUserDTO;
import com.ls.security.global.user.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {
    /**
     * @apiNote client 로부터 로그인 정보 받아서 인트라넷 id, pw 가져와 암호화해서 비교
     * @param userDTO
     * @return ResponseEntity<?>
     */
    public ResponseEntity<?> getToken(@RequestBody UserDTO userDTO);


    /**
     * @apiNote Android 로부터 로그인 정보 받아서 인트라넷 id, pw 가져와 암호화해서 비교
     * @param androidUserDTO
     * @return ResponseEntity<?>
     */
    public ResponseEntity<?> getTokenForAndroid(@RequestBody AndroidUserDTO androidUserDTO);
}
