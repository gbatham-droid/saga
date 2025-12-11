package com.example.service_a.service;

import com.example.service_a.entity.Employee;
import com.example.service_a.kafka.EmployeeProducer;
import com.example.service_a.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.example.event.EmployeeEvent;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo repo;
    private final EmployeeProducer producer;

    public String createEmployee(EmployeeEvent event) {
        Employee emp = new Employee(event.getId(), event.getName(), "CREATED");
        repo.save(emp);

        producer.publishEmployeeCreated(event);
        return "Employee created and event sent to Service-B";
    }
}
