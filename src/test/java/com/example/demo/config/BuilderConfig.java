package com.example.demo.config;

import com.example.demo.builder.UserDataTableBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.itmo.integration.http.datatables.DataTableBuilder;

import java.util.Map;

@Component
public class BuilderConfig {
    @Bean
    public Map<String, DataTableBuilder<?>> dataTableBuilders() {
        return Map.of("UserDataTableBuilder", new UserDataTableBuilder());
    }
}
