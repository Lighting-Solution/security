package com.ls.security.contact.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public interface ContactController {
    /**
     * @apiNote 최조 접속
     * @return ContactResponseDTO
     */
    @GetMapping("/list/all")
    ResponseEntity<?> getAll(HttpServletRequest request);

    /**
     * @apiNote 그룹, 검색 필터에 대한 조회
     * @return ContactResponseDTO
     */
    @GetMapping("/list/search")
    ResponseEntity<?> getAllBySearch(HttpServletRequest request);

    /**
     * @apiNote 안드로이드 EMP API
     * @return EmpAndroidDTO
     */
    @GetMapping("/list/all-emp/android")
    ResponseEntity<?> getEmpAllByAndroid(HttpServletRequest request);

    /**
     * @apiNote 안드로이드 PersonalContact API
     * @return ContactAndroidDTO
     */
    @GetMapping("/list/all-personal/android")
    ResponseEntity<?> getPersonalAllByAndroid(HttpServletRequest request);

    /**
     * @apiNote 사내 주소록-전체 주소록 연락처
     * @return List<EmpDTO>
     */
    @GetMapping("/list/all-emp")
    ResponseEntity<List<?>> getAllEmp(HttpServletRequest request);

    /**
     * @apiNote 개인 주소록-전체 주소록 연락처
     * @return List<PersonalContactDTO>
     */
    @GetMapping("/list/all-contact/{id}")
    ResponseEntity<List<?>> getAllPersonalContact(HttpServletRequest request);

    /**
     * @apiNote 사내 주소록-부서 별
     * @return List<EmpDTO>
     */
    @GetMapping("/list/group-emp/{departmentId}")
    ResponseEntity<List<?>> getAllEmpByDepartment(HttpServletRequest request);

    /**
     * @apiNote 개인 주소록-그룹 주소록 별
     * @return List<ContactGroupDTO>
     */
    @GetMapping("/list/group-contact/{groupId}")
    ResponseEntity<List<?>> getAllPersonalContactByGroup(HttpServletRequest request);

    /**
     * @apiNote 개인 연락처 추가
     * @return "success" 또는 "fail"
     */
    @PostMapping("/personal-contact")
    ResponseEntity<?> createPersonalContact(@RequestBody Map<?, ?> data, HttpServletRequest request);

    /**
     * @apiNote 개인 연락처 수정
     * @return PersonalContactDTO
     */
    @PutMapping("/personal-contact")
    ResponseEntity<?> updatePersonalContact(@RequestBody Map<?, ?> data, HttpServletRequest request);

    /**
     * @apiNote 개인 연락처 삭제
     * @return "success" 또는 "fail"
     */
    @DeleteMapping("/personal-contact")
    ResponseEntity<?> deletePersonalContact(@RequestBody Map<?, ?> data, HttpServletRequest request);


    /**
     * @apiNote 개인 주소록 목록 조회
     * @return List<PersonalGroupDTO>
     */
    @GetMapping("/personal-group")
    ResponseEntity<List<?>> getPersonalGroup(HttpServletRequest request);

    /**
     * @apiNote 개인 그룹 추가
     * @return "success" 또는 "fail"
     */
    @PostMapping("/personal-group")
    ResponseEntity<?> createPersonalGroup(@RequestBody Map<?, ?> data, HttpServletRequest request);

    /**
     * @apiNote 개인 그룹 수정
     * @return "success" 또는 "fail"
     */
    @PutMapping("/personal-group")
    ResponseEntity<?> updatePersonalGroup(@RequestBody Map<?, ?> data, HttpServletRequest request);

    /**
     * @apiNote 개인 그룹 삭제
     * @return "success" 또는 "fail"
     */
    @DeleteMapping("/group")
    ResponseEntity<?> deletePersonalGroup(HttpServletRequest request);

    /**
     * @apiNote 개인 연락처를 개인 그룹에 추가
     * @return "success" 또는 "fail"
     */
    @PostMapping("/contact-group")
    ResponseEntity<?> createContactGroup(@RequestBody Map<?, ?> data, HttpServletRequest request);

    /**
     * @apiNote 개인 연락처에서 개인 그룹 지정 해제
     * @return "success" 또는 "fail"
     */
    @DeleteMapping("/contact-group")
    ResponseEntity<?> deleteContactGroup(@RequestBody Map<?, ?> data, HttpServletRequest request);
}
