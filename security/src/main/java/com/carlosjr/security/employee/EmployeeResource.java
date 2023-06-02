package com.carlosjr.security.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeResource {

    @Autowired
    EmployeeRepository _employeeRepository;

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        Optional<Employee> employee = _employeeRepository.findById(id);
        return ResponseEntity.ok().body(employee);
    }

}
