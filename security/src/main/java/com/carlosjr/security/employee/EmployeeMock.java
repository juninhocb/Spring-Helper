package com.carlosjr.security.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class EmployeeMock implements CommandLineRunner {

    @Autowired
    EmployeeRepository _employeeRepository;

    @Override
    public void run(String... args) throws Exception {

        Employee e1 = new Employee(1L, "John Doe", false);
        Employee e2 = new Employee(2L, "Peter Brown",  true);
        Employee e3 = new Employee(3L, "James Blue", true);

        _employeeRepository.saveAll(Arrays.asList(e1,e2,e3));

    }
}
