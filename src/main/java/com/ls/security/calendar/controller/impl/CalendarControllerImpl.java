package com.ls.security.calendar.controller.impl;

import com.ls.security.calendar.controller.CalendarController;
import com.ls.security.calendar.service.CalendarService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lighting_solutions/security/calendar")
public class CalendarControllerImpl implements CalendarController {

    private final CalendarService calendarService;

    @Autowired
    public CalendarControllerImpl(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/events/*")
    public ResponseEntity<?> getEventById(HttpServletRequest request) {
        String requestURI=request.getRequestURI().replace("security/", "");
        String queryString = request.getQueryString();
        String fullURL = requestURI + (queryString != null ? "?" + queryString : "");
        return calendarService.getEventById(fullURL);
    }
}
