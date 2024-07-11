package com.ls.security.contact.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ContactService {

    ResponseEntity<?> getEmpALlByAndroid(String URI);
    ResponseEntity<?> getPersonalAllByAndroid(String URI);
    ResponseEntity<?> createPersonalContact(Map<? , ?> data, String URI);
    ResponseEntity<?> updatePersonalContact(Map<? , ?> data, String URI);
    ResponseEntity<?> deletePersonalContact(Map<? , ?> data, String URI);
}
