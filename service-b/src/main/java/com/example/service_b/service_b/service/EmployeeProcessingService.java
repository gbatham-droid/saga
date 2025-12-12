package com.example.service_b.service_b.service;

import com.example.service_b.service_b.entity.Employee;
import com.example.service_b.service_b.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.example.event.EmployeeEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@RequiredArgsConstructor
public class EmployeeProcessingService {

    private final EmployeeRepo repo;
    private final KafkaTemplate<String, EmployeeEvent> template;

    @Value("${topic.employee.rollback}")
    private String rollbackTopic;


    private final String SERVICE_B_CB = "serviceBProcessingCB";

    @CircuitBreaker(name = SERVICE_B_CB, fallbackMethod = "handleFailure")
    public void processEmployee(EmployeeEvent event, String processedTopic) {

        System.out.println("Service-B: Processing employee...");

        // simulate failure
        if (true) {
            throw new ArithmeticException("Service-B exception");
        }

        Employee emp = new Employee(event.getId(), event.getName(), "B_PROCESSED");
        repo.save(emp);

        template.send(processedTopic, event);
    }

    //fallback method
    public void handleFailure(EmployeeEvent event, String processedTopic, Throwable ex) {
        System.out.println("Service-B CircuitBreaker Fallback => sending ROLLBACK event");

        template.send(rollbackTopic, event);
    }
}
