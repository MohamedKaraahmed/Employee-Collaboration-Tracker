package com.example.employeecollaborationtracker.service;

import com.example.employeecollaborationtracker.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO findEmployeeById(Long id);

    List<EmployeeDTO> findAll();

    void updateEmployee(Long id, EmployeeDTO newEmployeeDTO);

    void deleteById(Long id);

}
