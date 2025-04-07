package com.example.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic usersTopic() {
        return TopicBuilder.name("demo.users").build();
    }

    @Bean
    public NewTopic productsTopic() {
        return TopicBuilder.name("demo.products").build();
    }

    @Bean
    public NewTopic createUserTopic() {
        return TopicBuilder.name("demo.create.user").build();
    }
}