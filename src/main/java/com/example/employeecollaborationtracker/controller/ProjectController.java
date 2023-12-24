package com.example.employeecollaborationtracker.controller;

import com.example.employeecollaborationtracker.dto.ProjectDTO;
import com.example.employeecollaborationtracker.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/project/add")
    public ResponseEntity<ProjectDTO> addProject(@Valid @RequestBody ProjectDTO newProjectDTO) {

        return ResponseEntity.ok(projectService.addProject(newProjectDTO));
    }

    @GetMapping("/project/findAll")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {

        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/project/get/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {

        return ResponseEntity.ok(projectService.findProjectById(id));
    }

    @PutMapping("/project/update/{id}")
    public void updateProject(@PathVariable Long id, @Valid @RequestBody ProjectDTO newProjectDTO) {

        projectService.updateProject(id, newProjectDTO);
    }

    @DeleteMapping("/project/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        projectService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}