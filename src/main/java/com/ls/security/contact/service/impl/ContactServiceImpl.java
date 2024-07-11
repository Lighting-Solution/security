package com.ls.security.contact.service.impl;

import com.ls.security.contact.service.ContactService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

    @Override
    public ResponseEntity<?> getEmpALlByAndroid(String URI) {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9000") // Ensure this base URL matches the target server's base URL
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient.get()
                .uri(URI)
                .retrieve()
                .bodyToMono(ResponseEntity.class)
                .block();
    }

    @Override
    public ResponseEntity<?> getPersonalAllByAndroid(String URI) {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9000") // Ensure this base URL matches the target server's base URL
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient.get()
                .uri(URI)
                .retrieve()
                .bodyToMono(ResponseEntity.class)
                .block();
    }
}
