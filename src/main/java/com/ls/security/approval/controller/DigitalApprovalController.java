package com.ls.security.approval.controller;

import com.ls.security.approval.dto.DigitalApprovalDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface DigitalApprovalController {
    /**
     * @apiNote intranet html form 태그 가져오기
     * @param request
     * @return String
     */
    public String getHtmlContent(HttpServletRequest request);

    /**
     * @apiNote intranet digital approval 결재 요청
     * @param data, request
     * @return ResponseEntity<?>
     */
    public ResponseEntity<?> approvalRequest(@RequestBody Map<?, ?> data, HttpServletRequest request);

    /**
     * @apiNote intranet digital approval List 요청
     * @return List<DigitalApproval>
     */
    public List<DigitalApprovalDTO> digitalApprovalAllList(HttpServletRequest request);

}
