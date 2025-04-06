package com.example.demo.kafka;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.integration.kafka.facades.KafkaFacade;

@Component
@Getter
@RequiredArgsConstructor
public class UserMessageFacade implements KafkaFacade {
    private final UserMessageListener listener;
    private final UserMessageAssertion assertions;
}
