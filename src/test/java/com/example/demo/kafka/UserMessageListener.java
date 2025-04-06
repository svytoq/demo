package com.example.demo.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itmo.integration.kafka.listeners.TestKafkaListener;

@Component
public class UserMessageListener extends TestKafkaListener {

    public UserMessageListener(@Value("demo.users") String topic) {
        super(topic);
    }
}