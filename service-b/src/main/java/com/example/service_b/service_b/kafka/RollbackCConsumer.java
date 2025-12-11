package com.example.service_b.service_b.kafka;

import com.example.service_b.service_b.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.example.event.EmployeeEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RollbackCConsumer {

    private final EmployeeRepo repo;

    @KafkaListener(topics = "${topic.employee.rollback}", groupId = "service-b")
    public void rollback(EmployeeEvent event) {
        System.out.println("Service-B rollback received");
        repo.deleteById(event.getId());
    }
}
