package com.ls.security.contact.controller.impl;

import com.ls.security.contact.controller.ContactController;
import com.ls.security.contact.service.ContactService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/lighting_solutions/security/contact")
public class ContactControllerImpl implements ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactControllerImpl(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllBySearch(HttpServletRequest request) {
        return null;
    }

    @Override
    @GetMapping("/list/all-emp/android")
    public ResponseEntity<?> getEmpAllByAndroid(HttpServletRequest request) {
        System.out.println("호출초훔ㄴㅇ;ㅣㅏ러");
        String requestURI=request.getRequestURI().replace("security/", "");
        String queryString = request.getQueryString();
        String fullURL = requestURI + (queryString != null ? "?" + queryString : "");
        return contactService.getEmpALlByAndroid(fullURL);
    }

    @Override
    @GetMapping("/list/all-personal/android")
    public ResponseEntity<?> getPersonalAllByAndroid(HttpServletRequest request) {
        System.out.println("호출초훔ㄴㅇ;ㅣㅏ러ㄷㅇㅇㅇㅇㄷㅇㄷㅇㄷ");
        String requestURI=request.getRequestURI().replace("security/", "");
        String queryString = request.getQueryString();
        String fullURL = requestURI + (queryString != null ? "?" + queryString : "");
        return contactService.getPersonalAllByAndroid(fullURL);
    }

    @Override
    public ResponseEntity<List<?>> getAllEmp(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<List<?>> getAllPersonalContact(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<List<?>> getAllEmpByDepartment(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<List<?>> getAllPersonalContactByGroup(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<String> createPersonalContact(Map<?, ?> data, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> updatePersonalContact(Map<?, ?> data, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> deletePersonalContact(Map<?, ?> data, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<List<?>> getPersonalGroup(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> createPersonalGroup(Map<?, ?> data, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> updatePersonalGroup(Map<?, ?> data, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> deletePersonalGroup(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> createContactGroup(Map<?, ?> data, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteContactGroup(Map<?, ?> data, HttpServletRequest request) {
        return null;
    }
}
