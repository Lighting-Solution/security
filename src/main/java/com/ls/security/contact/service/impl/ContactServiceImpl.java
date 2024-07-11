package com.ls.security.contact.service.impl;

import com.ls.security.contact.service.ContactService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

    private final WebClient webClient = WebClient.builder() // Ensure this base URL matches the target server's base URL
            .baseUrl("http://localhost:9000")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    @Override
    public ResponseEntity<?> getEmpALlByAndroid(String URI) {
        Object result = webClient.get()
                .uri(URI)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<?> getPersonalAllByAndroid(String URI) {
        Object result = webClient.get()
                .uri(URI)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
