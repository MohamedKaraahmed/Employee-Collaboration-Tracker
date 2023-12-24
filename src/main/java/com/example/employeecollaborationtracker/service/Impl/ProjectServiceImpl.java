package com.example.employeecollaborationtracker.service.Impl;

import com.example.employeecollaborationtracker.config.CustomException;
import com.example.employeecollaborationtracker.dto.ProjectDTO;
import com.example.employeecollaborationtracker.model.Project;
import com.example.employeecollaborationtracker.repository.ProjectRepository;
import com.example.employeecollaborationtracker.service.ProjectService;
import com.example.employeecollaborationtracker.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.employeecollaborationtracker.util.common.ExceptionMessages.INVALID_PROJECT_ID;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final CustomModelMapperImpl modelMapper;
    private final ValidationService validationService;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository,
                              CustomModelMapperImpl modelMapper,
                              ValidationService validationService) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.validationService = validationService;
    }

    @Override
    public ProjectDTO addProject(ProjectDTO project) {
        Project newProject = validationService.validateProject(project);

        return modelMapper.mapProjectToDTO(projectRepository.save(newProject));
    }

    @Override
    public ProjectDTO findProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new CustomException(String.format(INVALID_PROJECT_ID, id)));

        return modelMapper.mapProjectToDTO(project);
    }

    @Override
    public List<ProjectDTO> findAll() {

        return projectRepository.findAll()
                .stream()
                .map(modelMapper::mapProjectToDTO)
                .toList();
    }

    @Override
    @Modifying
    public void updateProject(Long id, ProjectDTO newProjectDTO) {
        Project updatedProject = projectRepository.findById(id).orElse(null);
        if (updatedProject == null) {
            throw new CustomException(String.format(INVALID_PROJECT_ID, id));

        }
        //update project only if exist
        updatedProject.setTitle(newProjectDTO.getTitle());
        projectRepository.save(updatedProject);
    }

    @Override
    public void deleteById(Long id) {
        validationService.validateProjectDeletion(id);
    }
}

