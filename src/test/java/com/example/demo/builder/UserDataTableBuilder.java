package com.example.demo.builder;

import com.example.demo.model.User;
import ru.itmo.integration.http.datatables.DataTableBuilder;

import java.util.List;

public class UserDataTableBuilder extends DataTableBuilder<User> {
    @Override
    protected List<String> dataTableHeader() {
        return List.of("id", "name", "email");
    }

    @Override
    protected List<String> dataTableData(User user) {
        return List.of(user.getId().toString(), user.getName(), user.getEmail());
    }
}
