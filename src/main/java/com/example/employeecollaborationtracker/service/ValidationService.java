package com.example.employeecollaborationtracker.service;

import com.example.employeecollaborationtracker.dto.EmployeeDTO;
import com.example.employeecollaborationtracker.dto.ProjectAssignmentDTO;
import com.example.employeecollaborationtracker.dto.ProjectDTO;
import com.example.employeecollaborationtracker.model.Employee;
import com.example.employeecollaborationtracker.model.Project;
import com.example.employeecollaborationtracker.model.ProjectAssignment;

import java.util.Date;

public interface ValidationService {

    ProjectAssignment validateAddAssignment(ProjectAssignmentDTO projectAssignment);

    Employee validateEmployee(EmployeeDTO employeeDTO);

    Project validateProject(ProjectDTO project);

    void validateCSVRecord(String[] CSVRecord);

    boolean isEmployeeInDB(Long id);

    boolean isEmployeeInDB(String email);

    boolean isProjectInDB(Long id);

    boolean isProjectInDB(String title);

    boolean isAssignmentInDB(Long employeeId, Long projectId, Date dateFrom, Date dateTo);

    void validateEmployeeDeletion(Long id);

    void validateProjectDeletion(Long id);

    void validateAssignmentDeletion(Long id);

    ProjectAssignment validateUpdateAssignment(Long id, ProjectAssignmentDTO projectAssignmentDTO);
}