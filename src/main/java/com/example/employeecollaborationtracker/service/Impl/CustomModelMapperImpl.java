package com.example.employeecollaborationtracker.service.Impl;

import com.example.employeecollaborationtracker.dto.EmployeeDTO;
import com.example.employeecollaborationtracker.dto.ProjectAssignmentDTO;
import com.example.employeecollaborationtracker.dto.ProjectDTO;
import com.example.employeecollaborationtracker.model.Employee;
import com.example.employeecollaborationtracker.model.Project;
import com.example.employeecollaborationtracker.model.ProjectAssignment;
import com.example.employeecollaborationtracker.service.CustomModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomModelMapperImpl implements CustomModelMapper {

    private final org.modelmapper.ModelMapper modelMapper = new org.modelmapper.ModelMapper();

    @Override
    public EmployeeDTO mapEmployeeToDTO(Employee employee) {

        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public ProjectDTO mapProjectToDTO(Project project) {

        return modelMapper.map(project, ProjectDTO.class);
    }

    @Override
    public ProjectAssignmentDTO mapProjectAssignmentToDTO(ProjectAssignment projectAssignment) {

        return modelMapper.map(projectAssignment, ProjectAssignmentDTO.class);
    }

}
