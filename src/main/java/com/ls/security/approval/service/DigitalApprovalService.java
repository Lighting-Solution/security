package com.ls.security.approval.service;

import com.ls.security.approval.dto.DigitalApprovalDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface DigitalApprovalService {
    String sendForm(String URI);
    ResponseEntity approvalRequest(Map<?, ?> data, String URI);

    List<DigitalApprovalDTO> digitalApprovalAllList(String URI);
}
