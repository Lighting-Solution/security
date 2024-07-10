package com.ls.security.approval.controller.impl;

import com.ls.security.approval.controller.DigitalApprovalController;
import com.ls.security.approval.service.DigitalApprovalService;
import com.ls.security.global.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/lighting_solutions/security/digital/approval")
public class DigitalApprovalControllerImpl implements DigitalApprovalController {
    private final DigitalApprovalService digitalApprovalService;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public DigitalApprovalControllerImpl(DigitalApprovalService digitalApprovalService, UserServiceImpl userServiceImpl){
        this.digitalApprovalService = digitalApprovalService;
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping("/form")
    public String getHtmlContent(HttpServletRequest request){
        System.out.println("들어옴");
        System.out.println("URI : " + request.getRequestURI());
        String requestURI=request.getRequestURI().replace("security/", "");
        String queryString = request.getQueryString();
        String fullURL = requestURI + (queryString != null ? "?" + queryString : "");
        System.out.println("fullURL : " + fullURL);

        return digitalApprovalService.sendForm(fullURL);
    }

    @PostMapping("/request")
    public ResponseEntity<?> approvalRequest(@RequestBody Map<?, ?> data, HttpServletRequest request) {
        System.out.println("URI : " + request.getRequestURI().replace("security/", ""));
        System.out.println("data : " + data);
        return digitalApprovalService.approvalRequest(data, request.getRequestURI().replace("security/", ""));
    }



}
