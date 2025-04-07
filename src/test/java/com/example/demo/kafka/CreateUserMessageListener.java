package com.example.demo.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itmo.integration.kafka.listeners.TestKafkaListener;

@Component
public class CreateUserMessageListener extends TestKafkaListener {

    public CreateUserMessageListener(@Value("demo.create.user") String topic) {
        super(topic);
    }
}