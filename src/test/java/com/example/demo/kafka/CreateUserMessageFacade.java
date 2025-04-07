package com.example.demo.kafka;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.integration.kafka.facades.KafkaFacade;

@Component
@Getter
@RequiredArgsConstructor
public class CreateUserMessageFacade implements KafkaFacade {
    private final CreateUserMessageListener listener;
    private final CreateUserMessageAssertion assertions;
}
