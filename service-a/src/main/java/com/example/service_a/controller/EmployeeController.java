package com.example.service_a.controller;

import com.example.service_a.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.example.event.EmployeeEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody EmployeeEvent event) {
        return ResponseEntity.ok(service.createEmployee(event));
    }
}
