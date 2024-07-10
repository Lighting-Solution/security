package com.ls.security.global.user.service;

import com.ls.security.global.user.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    /**
     * @apiNote resources에 저장되어 있는 html 폼 가져오기
     * @param accountId, accountPw
     * @return
     */
    ResponseEntity<UserDTO> toIntranet(String accountId, String accountPw);

    /**
     * @apiNote Intranet 으로 부터 받은 정보 security DB에 id, pw 저장하기
     * @param responseIntra
     * @return
     */
    UserDTO userSave(UserDTO responseIntra);


}
