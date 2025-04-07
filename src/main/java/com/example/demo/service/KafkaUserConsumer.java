package com.example.demo.service;

import com.example.demo.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaUserConsumer {
    private final UserService userService;

    @KafkaListener(topics = "demo.create.user", groupId = "demo-group")
    public void consumeCreateUserMessage(UserDto userDto) {
        userService.createUserFromKafka(userDto);
    }
}