package com.ls.security.approval.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DigitalApprovalService {
    String sendForm(String URI);
    ResponseEntity approvalRequest(Map<?, ?> data, String URI);
}
