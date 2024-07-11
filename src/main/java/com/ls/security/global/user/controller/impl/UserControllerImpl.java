package com.ls.security.global.user.controller.impl;

import com.ls.security.global.user.controller.UserController;
import com.ls.security.global.user.dto.AndroidUserDTO;
import com.ls.security.global.user.dto.UserDTO;
import com.ls.security.global.user.service.impl.UserServiceImpl;
import com.ls.security.global.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/lighting_solutions/security")
public class UserControllerImpl implements UserController {

    private final UserServiceImpl userServiceImpl;
    private final AuthenticationProvider authenticationProvider;
    private final JwtService jwtService;

    private String token;

    @Autowired
    public UserControllerImpl(UserServiceImpl userServiceImpl, AuthenticationProvider authenticationProvider, JwtService jwtService) {
        this.userServiceImpl = userServiceImpl;
        this.authenticationProvider = authenticationProvider;
        this.jwtService = jwtService;
    }

    @Override
    @PostMapping(value="/login")
    public ResponseEntity<?> getToken(@RequestBody UserDTO userDTO) {
        System.out.println("userDTO: " + userDTO);

        try {
            // Intranet에서 저장된 ID, PW 가져오기 (복호화 전)
            ResponseEntity<UserDTO> intraUserDTO = userServiceImpl.toIntra(userDTO.getAccountId(), userDTO.getAccountPw());
            UserDTO responseIntra = intraUserDTO.getBody();

            // 가져온 ID와 PW를 이용해 security server DB에 저장
            UserDTO saveUserDTO = userServiceImpl.userSave(responseIntra);

            // Client 비밀번호와 DB에 저장된 비밀번호 비교
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            // Intranet에서 가져온 Id, PW
            System.out.println("saveUserDTO: " + saveUserDTO);

            boolean isPasswordMatch = encoder.matches(userDTO.getAccountPw(), saveUserDTO.getAccountPw());

            if (isPasswordMatch) {
                System.out.println("password match");
                UsernamePasswordAuthenticationToken credit = new UsernamePasswordAuthenticationToken(
                        userDTO.getAccountId(),
                        userDTO.getAccountPw()
                );

                Authentication auth = authenticationProvider.authenticate(credit);

                // 토큰 생성
                String jwts = jwtService.getToken(auth.getName());
                return ResponseEntity.ok()
                        .header(HttpHeaders.AUTHORIZATION, jwts)
                        .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                        .body(Map.of("token", jwts,
                                "empId", String.valueOf(saveUserDTO.getEmpId()),
                                "positionId", String.valueOf(saveUserDTO.getPositionId()),
                                "empName", saveUserDTO.getEmpName(),
                                "departmentId", saveUserDTO.getDepartmentId(),
                                "empAdmin" , saveUserDTO.getEmpAdmin(),
                                "departmentName", saveUserDTO.getDepartmentName(),
                                "empEmail" , saveUserDTO.getEmpEmail()
                        ));
            } else {
                System.out.println("password not match");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            System.out.println("Error during authentication: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

    @Override
    @PostMapping(value="/android/login")
    public ResponseEntity<?> getTokenForAndroid(AndroidUserDTO androidUserDTO) {
        System.out.println("userDTO: " + androidUserDTO);

        try {
            // Intranet에서 저장된 ID, PW 가져오기 (복호화 전)
            ResponseEntity<UserDTO> intraUserDTO = userServiceImpl.toIntra(androidUserDTO.getAccountId(), androidUserDTO.getAccountPw());
            UserDTO responseIntra = intraUserDTO.getBody();

            // 가져온 ID와 PW를 이용해 security server DB에 저장
            UserDTO saveUserDTO = userServiceImpl.userSave(responseIntra);

            // Client 비밀번호와 DB에 저장된 비밀번호 비교
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            // Intranet에서 가져온 Id, PW
            System.out.println("saveUserDTO: " + saveUserDTO);

            boolean isPasswordMatch = encoder.matches(androidUserDTO.getAccountPw(), saveUserDTO.getAccountPw());

            if (isPasswordMatch) {
                System.out.println("password match");
                UsernamePasswordAuthenticationToken credit = new UsernamePasswordAuthenticationToken(
                        androidUserDTO.getAccountId(),
                        androidUserDTO.getAccountPw()
                );

                Authentication auth = authenticationProvider.authenticate(credit);

                // 토큰 생성
                String jwts = jwtService.getToken(auth.getName());
                return ResponseEntity.ok()
                        .header(HttpHeaders.AUTHORIZATION, jwts)
                        .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                        .body(Map.of("token", jwts,
                                "empId", String.valueOf(saveUserDTO.getEmpId()),
                                "positionId", String.valueOf(saveUserDTO.getPositionId()),
                                "empName", saveUserDTO.getEmpName(),
                                "departmentId", saveUserDTO.getDepartmentId()
                        ));
            } else {
                System.out.println("password not match");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            System.out.println("Error during authentication: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

}
