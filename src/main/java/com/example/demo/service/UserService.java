package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final KafkaTemplate<String, UserDto> kafkaTemplate;
    private final S3Service s3Service;
    private static final String TOPIC = "demo.users";

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        s3Service.saveUserToS3(userDto);

        sendUserToKafka(userDto);
        return userRepository.save(user);
    }

    public void sendUserToKafka(UserDto userDto) {
        kafkaTemplate.send(TOPIC, userDto);
    }
}