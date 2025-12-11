package com.example.service_c.service_c.kafka;

import com.example.service_c.service_c.entity.Employee;
import com.example.service_c.service_c.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.example.event.EmployeeEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BProcessedConsumer {

    private final EmployeeRepo repo;
    private final KafkaTemplate<String, EmployeeEvent> template;

    @Value("${topic.employee.rollback}")
    private String rollbackTopic;

    @KafkaListener(topics = "employee.b.processed", groupId = "service-c")
    public void consume(EmployeeEvent event) {
        try {
//            event.setName("FAIL1");
            // simulate failure if needed
            if(event.getName().equals("FAIL"))
                throw new RuntimeException("Failure in Service-C");

            Employee emp = new Employee(event.getId(), event.getName(), "COMPLETED");
            repo.save(emp);

        } catch(Exception ex) {
            System.out.println("Service-C failed -> sending rollback");
            template.send(rollbackTopic, event);
        }
    }
}
