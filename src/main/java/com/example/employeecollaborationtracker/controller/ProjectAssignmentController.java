package com.example.employeecollaborationtracker.controller;

import com.example.employeecollaborationtracker.dto.ProjectAssignmentDTO;
import com.example.employeecollaborationtracker.dto.ReportDTO;
import com.example.employeecollaborationtracker.service.AssignmentService;
import com.example.employeecollaborationtracker.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class ProjectAssignmentController {

    private final AssignmentService assignmentService;
    private final ReportService reportService;

    @Autowired
    public ProjectAssignmentController(AssignmentService assignmentService, ReportService reportService) {
        this.assignmentService = assignmentService;
        this.reportService = reportService;
    }

    @PostMapping("/assignment/add")
    public ResponseEntity<ProjectAssignmentDTO> addAssignment(@Valid @RequestBody ProjectAssignmentDTO newAssignmentDTO) {

        return ResponseEntity.ok(assignmentService.addAssignment(newAssignmentDTO));
    }

    @GetMapping("/assignment/findAll")
    public ResponseEntity<List<ProjectAssignmentDTO>> getAllAssignments() {
        if (assignmentService.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(assignmentService.findAll());
    }

    @GetMapping("/assignment/get/{id}")
    public ResponseEntity<ProjectAssignmentDTO> getAssignmentById(@PathVariable Long id) {

        return ResponseEntity.ok(assignmentService.findAssignmentById(id));
    }

    @PutMapping("/assignment/update/{id}")
    public void updateAssignment(@PathVariable Long id, @Valid @RequestBody ProjectAssignmentDTO newAssignmentDTO) {

        assignmentService.updateAssignment(id, newAssignmentDTO);
    }

    @DeleteMapping("/assignment/delete/{id}")
    public ResponseEntity<?> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/assignment/report")
    public ResponseEntity<List<ReportDTO>> getReport() {

        return ResponseEntity.ok(reportService.getEmployeesOverlapReport());
    }

}
