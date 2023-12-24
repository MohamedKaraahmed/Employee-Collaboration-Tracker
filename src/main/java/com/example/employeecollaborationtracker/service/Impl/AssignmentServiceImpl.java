package com.example.employeecollaborationtracker.service.Impl;

import com.example.employeecollaborationtracker.config.CustomException;
import com.example.employeecollaborationtracker.dto.ProjectAssignmentDTO;
import com.example.employeecollaborationtracker.model.ProjectAssignment;
import com.example.employeecollaborationtracker.repository.ProjectAssignmentRepository;
import com.example.employeecollaborationtracker.service.AssignmentService;
import com.example.employeecollaborationtracker.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.employeecollaborationtracker.util.common.ExceptionMessages.ASSIGNMENT_NOT_EXIST;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final ProjectAssignmentRepository projectAssignmentRepository;
    private final CustomModelMapperImpl modelMapper;
    private final ValidationService validationService;

    @Autowired
    public AssignmentServiceImpl(ProjectAssignmentRepository projectAssignmentRepository,
                                 CustomModelMapperImpl modelMapper,
                                 ValidationService validationService) {
        this.projectAssignmentRepository = projectAssignmentRepository;
        this.modelMapper = modelMapper;
        this.validationService = validationService;
    }

    @Override
    public ProjectAssignmentDTO addAssignment(ProjectAssignmentDTO assignment) {
        ProjectAssignment newAssignment = validationService.validateAddAssignment(assignment);

        return modelMapper.mapProjectAssignmentToDTO(projectAssignmentRepository.save(newAssignment));
    }

    @Override
    public ProjectAssignmentDTO findAssignmentById(Long id) {
        ProjectAssignment assignment = projectAssignmentRepository.findById(id)
                .orElseThrow(() -> new CustomException(String.format(ASSIGNMENT_NOT_EXIST, id)));

        return modelMapper.mapProjectAssignmentToDTO(assignment);
    }

    @Override
    public List<ProjectAssignmentDTO> findAll() {

        return projectAssignmentRepository.findAll()
                .stream()
                .map(modelMapper::mapProjectAssignmentToDTO)
                .toList();
    }

    @Override
    @Modifying
    public void updateAssignment(Long id, ProjectAssignmentDTO newAssignmentDTO) {
        ProjectAssignment projectAssignment = projectAssignmentRepository.findById(id)
                .orElse(null);

        if (projectAssignment == null) {

            throw new CustomException(String.format(ASSIGNMENT_NOT_EXIST, id));
        }
        //process update validation only if the assignment exist
        ProjectAssignment updatedAssignment = validationService.validateUpdateAssignment(id,newAssignmentDTO);

        projectAssignmentRepository.save(updatedAssignment);
    }

    @Override
    public void deleteById(Long id) {

        validationService.validateAssignmentDeletion(id);
    }


}