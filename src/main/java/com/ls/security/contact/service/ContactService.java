package com.ls.security.contact.service;

import org.springframework.http.ResponseEntity;

public interface ContactService {

    ResponseEntity getEmpALlByAndroid(String URI);
    ResponseEntity getPersonalAllByAndroid(String URI);
}
