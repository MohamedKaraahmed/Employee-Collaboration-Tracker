package com.example.employeecollaborationtracker.service.Impl;

import com.example.employeecollaborationtracker.config.CustomException;
import com.example.employeecollaborationtracker.dto.EmployeeDTO;
import com.example.employeecollaborationtracker.model.Employee;
import com.example.employeecollaborationtracker.repository.EmployeeRepository;
import com.example.employeecollaborationtracker.service.EmployeeService;
import com.example.employeecollaborationtracker.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.employeecollaborationtracker.util.common.ExceptionMessages.INVALID_EMPLOYEE;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final CustomModelMapperImpl modelMapper;
    private final ValidationService validationService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               CustomModelMapperImpl modelMapper,
                               ValidationService validationService) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.validationService = validationService;
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee newEmployee = validationService.validateEmployee(employeeDTO);

        return modelMapper.mapEmployeeToDTO(employeeRepository.save(newEmployee));
    }

    @Override
    public EmployeeDTO findEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new CustomException(String.format(INVALID_EMPLOYEE, id)));

        return modelMapper.mapEmployeeToDTO(employee);
    }

    @Override
    public List<EmployeeDTO> findAll() {

        return employeeRepository.findAll()
                .stream()
                .map(modelMapper::mapEmployeeToDTO)
                .toList();
    }

    @Override
    @Modifying
    public void updateEmployee(Long id, EmployeeDTO newEmployeeDTO) {
        Employee updatedEmployee = employeeRepository.findById(id).orElse(null);
        if (updatedEmployee == null) {
            throw new CustomException(String.format(INVALID_EMPLOYEE, id));

        }
        //update employee only if exist
        updatedEmployee.setEmail(newEmployeeDTO.getEmail());
        employeeRepository.save(updatedEmployee);
    }

    @Override
    public void deleteById(Long id) {
        validationService.validateEmployeeDeletion(id);

    }
}

