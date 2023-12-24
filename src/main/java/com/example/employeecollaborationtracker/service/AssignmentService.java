package com.example.employeecollaborationtracker.service;

import com.example.employeecollaborationtracker.dto.ProjectAssignmentDTO;

import java.util.List;

public interface AssignmentService {
    ProjectAssignmentDTO addAssignment(ProjectAssignmentDTO assignment);

    ProjectAssignmentDTO findAssignmentById(Long id);

    List<ProjectAssignmentDTO> findAll();

    void updateAssignment(Long id, ProjectAssignmentDTO newAssignmentDTO);

    void deleteById(Long id);

}
