package com.example.demo.kafka;

import org.springframework.stereotype.Component;
import ru.itmo.integration.kafka.jsonassertions.JsonAssertionsImpl;
import ru.itmo.platform.utils.mapping.ObjectMapperHelper;

import java.util.Set;

@Component
public class CreateUserMessageAssertion extends JsonAssertionsImpl {
    public CreateUserMessageAssertion(ObjectMapperHelper objectMapperHelper) {
        super(objectMapperHelper, Set.of());
    }
}