package com.example.employeecollaborationtracker.service;

import com.example.employeecollaborationtracker.dto.EmployeeDTO;
import com.example.employeecollaborationtracker.dto.ProjectAssignmentDTO;
import com.example.employeecollaborationtracker.dto.ProjectDTO;
import com.example.employeecollaborationtracker.model.Employee;
import com.example.employeecollaborationtracker.model.Project;
import com.example.employeecollaborationtracker.model.ProjectAssignment;

public interface CustomModelMapper {
    EmployeeDTO mapEmployeeToDTO(Employee employee);

    ProjectDTO mapProjectToDTO(Project project);

    ProjectAssignmentDTO mapProjectAssignmentToDTO(ProjectAssignment projectAssignment);

}