package com.example.service_a.kafka;

import com.example.service_a.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.example.event.EmployeeEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RollbackConsumer {

    private final EmployeeRepo repo;

    @KafkaListener(topics = "${topic.employee.rollback}", groupId = "service-a")
    public void rollback(EmployeeEvent event) {
        System.out.println("Service-A rollback received");
        repo.deleteById(event.getId());
    }
}