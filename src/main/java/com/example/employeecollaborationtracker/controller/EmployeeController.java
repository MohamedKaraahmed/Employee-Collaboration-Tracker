package com.example.employeecollaborationtracker.controller;

import com.example.employeecollaborationtracker.dto.EmployeeDTO;
import com.example.employeecollaborationtracker.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired 
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee/add")
    public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO newEmployeeDTO) {

        return new ResponseEntity<>(employeeService.addEmployee(newEmployeeDTO), HttpStatus.CREATED);
    }

    @GetMapping("/employee/findAll")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {

        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/employee/get/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {

        return ResponseEntity.ok(employeeService.findEmployeeById(id));
    }

    @PutMapping("/employee/update/{id}")
    public void updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO newEmployeeDTO) {

        employeeService.updateEmployee(id, newEmployeeDTO);
    }

    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
