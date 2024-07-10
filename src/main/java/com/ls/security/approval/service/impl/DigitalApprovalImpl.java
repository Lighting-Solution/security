package com.ls.security.approval.service.impl;

import com.ls.security.approval.service.DigitalApprovalService;
import com.ls.security.global.user.dto.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.Map;


@Service
public class DigitalApprovalImpl implements DigitalApprovalService {
    @Override
    public String sendForm(String URI) {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9000") // Ensure this base URL matches the target server's base URL
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient.get()
                .uri(URI)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public ResponseEntity<?> approvalRequest(Map<?, ?> data, String URI) {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9000")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient.post()
                .uri(URI)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(data)
                .retrieve()
                .bodyToMono(ResponseEntity.class)
                .block();
    }
}

