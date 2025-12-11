package com.example.service_b.service_b.kafka;

import com.example.service_b.service_b.entity.Employee;
import com.example.service_b.service_b.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.example.event.EmployeeEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeCreatedConsumer {

    private final EmployeeRepo repo;
    private final KafkaTemplate<String, EmployeeEvent> template;

    @Value("${topic.employee.b.processed}")
    private String processedTopic;

    @Value("${topic.employee.rollback}")
    private String rollbackTopic;

    @KafkaListener(topics = "${topic.employee.created}", groupId = "service-b")
    public void consume(EmployeeEvent event) {
        try {
            System.out.println("message received in service-b");
            Employee emp = new Employee(event.getId(), event.getName(), "B_PROCESSED");
            if(1==1)throw new ArithmeticException("service B exception");
            repo.save(emp);
            template.send(processedTopic, event);
        } catch (Exception e) {
            System.out.println("Service-B failed -> sending ROLLBACK");
            template.send(rollbackTopic, event);
        }
    }
}
