package com.ls.security.calendar.service;

import org.springframework.http.ResponseEntity;

public interface CalendarService {
    public ResponseEntity<?> getEventById(String URI);
}
