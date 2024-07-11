package com.ls.security.global.user.service.impl;
import com.ls.security.global.user.domain.dao.UserDAO;

import com.ls.security.global.user.domain.model.User;
import com.ls.security.global.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public ResponseEntity<UserDTO> toIntra(String accountId, String accountPw) {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9000")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        UserDTO userDTO = new UserDTO();
        userDTO.setAccountId(accountId);
        userDTO.setAccountPw(accountPw);

        return webClient.post()
                .uri("/api/v1/intranet/emp/toIntra")
                .bodyValue(userDTO)
                .retrieve()
                .toEntity(UserDTO.class)
                .block();
    }
    public UserDTO userSave(UserDTO responseIntra) {
        User user = new User();
        user.setAccountId(responseIntra.getAccountId());

        System.out.println("userSave : " + responseIntra.getAccountPw());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(responseIntra.getAccountPw());
        user.setAccountPw(password);
        Optional<User> checkId = userDAO.findByAccountId(user.getAccountId());
        User savedUser = checkId.orElseGet(() -> userDAO.save(user));

        UserDTO userDTO = new UserDTO();
        userDTO.setEmpId(responseIntra.getEmpId());
        userDTO.setPositionId(responseIntra.getPositionId());
        userDTO.setAccountId(savedUser.getAccountId());
        userDTO.setAccountPw(savedUser.getAccountPw());
        userDTO.setEmpName(responseIntra.getEmpName());
        userDTO.setDepartmentId(responseIntra.getDepartmentId());
        userDTO.setEmpAdmin(responseIntra.getEmpAdmin());
        userDTO.setDepartmentName(responseIntra.getDepartmentName());
        userDTO.setEmpEmail(responseIntra.getEmpEmail());
        return userDTO;
    }


    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        Optional<User> user = userDAO.findByAccountId(accountId);
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;

        if(user.isPresent()) {
            User currentUser = user.get();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            String currentPassword = currentUser.getAccountPw();
            /*비밀번호 암호화하여 다시 user객체에 저장.*/
            if (currentPassword.matches("^\\$2(a|y|b|x)\\$\\d{2}\\$.+")) {
                System.out.println("This is a bcrypt hash.");
            } else {
                System.out.println("This is not a bcrypt hash.");
                // 해쉬가 아닐 경우 해쉬로 바꿔준뒤 저장
                String hashPw = encoder.encode(currentUser.getAccountPw());
                currentUser.setAccountPw(hashPw);
                userDAO.save(currentUser);
            }

            builder = org.springframework.security.core.userdetails.User.withUsername(accountId);
            builder.password(currentUser.getAccountPw());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }



}

