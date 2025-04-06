package com.example.demo.kafka;

import org.springframework.stereotype.Component;
import ru.itmo.integration.kafka.jsonassertions.JsonAssertionsImpl;
import ru.itmo.platform.utils.mapping.ObjectMapperHelper;

import java.util.Set;

@Component
public class UserMessageAssertion extends JsonAssertionsImpl {

    public UserMessageAssertion(ObjectMapperHelper objectMapperHelper) {
        super(objectMapperHelper, Set.of());
    }
}