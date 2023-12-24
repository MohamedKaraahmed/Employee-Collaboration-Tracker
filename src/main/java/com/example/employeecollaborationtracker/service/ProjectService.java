package com.example.employeecollaborationtracker.service;

import com.example.employeecollaborationtracker.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {
    ProjectDTO addProject(ProjectDTO newProject);

    ProjectDTO findProjectById(Long id);

    List<ProjectDTO> findAll();

    void updateProject(Long id, ProjectDTO newProjectDTO);

    void deleteById(Long id);

}