package com.example.service_a.kafka;

import lombok.RequiredArgsConstructor;
import org.example.event.EmployeeEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeProducer {

    private final KafkaTemplate<String, EmployeeEvent> template;

    @Value("${topic.employee.created}")
    private String createdTopic;

    public void publishEmployeeCreated(EmployeeEvent event) {
        template.send(createdTopic, event);
    }
}