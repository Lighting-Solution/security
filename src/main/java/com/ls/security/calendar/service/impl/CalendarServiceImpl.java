package com.ls.security.calendar.service.impl;

import com.ls.security.calendar.service.CalendarService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service("calendarService")
public class CalendarServiceImpl implements CalendarService {

    private final WebClient webClient = WebClient.builder() // Ensure this base URL matches the target server's base URL
            .baseUrl("http://localhost:9000")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    @Override
    public ResponseEntity<?> getEventById(String URI) {
        Object result = webClient.get()
                .uri(URI)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
