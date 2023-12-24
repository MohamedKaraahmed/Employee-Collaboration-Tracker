package com.example.employeecollaborationtracker.service.Impl;

import com.example.employeecollaborationtracker.config.CustomException;
import com.example.employeecollaborationtracker.dto.EmployeeDTO;
import com.example.employeecollaborationtracker.dto.ProjectAssignmentDTO;
import com.example.employeecollaborationtracker.dto.ProjectDTO;
import com.example.employeecollaborationtracker.model.Employee;
import com.example.employeecollaborationtracker.model.Project;
import com.example.employeecollaborationtracker.model.ProjectAssignment;
import com.example.employeecollaborationtracker.repository.EmployeeRepository;
import com.example.employeecollaborationtracker.repository.ProjectAssignmentRepository;
import com.example.employeecollaborationtracker.repository.ProjectRepository;
import com.example.employeecollaborationtracker.service.DateValidator;
import com.example.employeecollaborationtracker.service.ValidationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

import static com.example.employeecollaborationtracker.util.common.ExceptionMessages.*;
import static com.example.employeecollaborationtracker.util.constants.ConstantValues.CORRECT_SEPARATED_VALUES_COUNT;

@Service
public class ValidationServiceImpl implements ValidationService {

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final ProjectAssignmentRepository projectAssignmentRepository;
    private final DateValidator dateValidator;

    @Autowired
    public ValidationServiceImpl(EmployeeRepository employeeRepository,
                                 ProjectRepository projectRepository,
                                 ProjectAssignmentRepository projectAssignmentRepository,
                                 DateValidator dateValidator) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.projectAssignmentRepository = projectAssignmentRepository;
        this.dateValidator = dateValidator;
    }

    @Override
    @Transactional
    public void validateCSVRecord(String[] CSVRecord) {
        if (CSVRecord.length != CORRECT_SEPARATED_VALUES_COUNT) {
            throw new CustomException(INVALID_CSV_RECORD_VALUES_COUNT);
        }
        //process the csv record
        String employeeEmail = CSVRecord[0];
        String projectTitle = CSVRecord[1];
        String dateFromAsString = CSVRecord[2].trim();
        String dateToAsString = CSVRecord[3].trim();

        Date dateFrom = dateFromAsString.equalsIgnoreCase("null") || dateFromAsString.trim()
                .isEmpty()
                ? Calendar.getInstance().getTime()
                : dateValidator.validateDate(dateFromAsString);

        Date dateTo = dateToAsString.equalsIgnoreCase("null") || dateToAsString.trim()
                .isEmpty()
                ? Calendar.getInstance().getTime()
                : dateValidator.validateDate(dateToAsString);

        if (dateTo.before(dateFrom)) {
            throw new CustomException(String.format(INVALID_DATE_TO, dateToAsString));
        }

        //if employee does not exist in DB -> adds new employee
        Employee employee = employeeRepository.findEmployeeByEmail(employeeEmail)
                .orElseGet(() -> {
                    Employee employee1 = new Employee();
                    employee1.setEmail(employeeEmail);
                    return employeeRepository
                            .save(employee1);
                });

        //if project does not exist in DB -> adds new project
        Project project = projectRepository.findProjectByTitle(projectTitle)
                .orElseGet(() -> {
                    Project project1 = new Project();
                    project1.setTitle(projectTitle);
                    return projectRepository.save(project1);
                });

        //if the same assigment is not in the DB adds it
        ProjectAssignment isAssignmentExists = projectAssignmentRepository.
                findProjectAssignmentByEmployeeIdAndProjectIdAndDateFromAndDateTo(
                        employee.getId(),
                        project.getId(),
                        dateFrom,
                        dateTo);

        if (isAssignmentExists != null) {
            throw new CustomException(INVALID_ASSIGNMENT);
        }

        //add the assignment in DB
        ProjectAssignment newAssignment = new ProjectAssignment.Builder()
                .employee(employee)
                .project(project)
                .dateFrom(dateFrom)
                .dateTo(dateTo)
                .build();

        projectAssignmentRepository.save(newAssignment);
    }

    @Override
    public ProjectAssignment validateAddAssignment(ProjectAssignmentDTO assignmentDTO) {
        //validate if employee exist in DB
        if (isEmployeeInDB(assignmentDTO.getEmployeeId())) {
            throw new CustomException(String.format(INVALID_EMPLOYEE, assignmentDTO
                    .getEmployeeId()));
        }

        //validate if project exist in DB
        if (isProjectInDB(assignmentDTO.getProjectId())) {
            throw new CustomException(String.format(INVALID_PROJECT_ID, assignmentDTO
                    .getProjectId()));
        }

        //validate if the same assignment already exist
        if (isAssignmentInDB(assignmentDTO.getEmployeeId(),
                assignmentDTO.getProjectId(),
                assignmentDTO.getDateFrom(),
                assignmentDTO.getDateTo())) {
            throw new CustomException(INVALID_ASSIGNMENT);
        }

        Employee assignmentEmployee = employeeRepository.findById(assignmentDTO.getEmployeeId())
                .orElse(null);

        Project assignmentProject = projectRepository.findById(assignmentDTO.getProjectId())
                .orElse(null);

        // return built assignment
        return new ProjectAssignment.Builder()
                .employee(assignmentEmployee)
                .project(assignmentProject)
                .dateFrom(assignmentDTO.getDateFrom())
                .dateTo(assignmentDTO.getDateTo())
                .build();
    }

    @Override
    public Employee validateEmployee(EmployeeDTO employeeDTO) {
        if (isEmployeeInDB(employeeDTO.getEmail())) {
            throw new CustomException(String.format(INVALID_EMPLOYEE_ADD, employeeDTO.getEmail()));
        }

        Employee employee = new Employee();
        employee.setEmail(employeeDTO.getEmail());
        return employee;
    }

    @Override
    public Project validateProject(ProjectDTO projectDTO) {
        if (isProjectInDB(projectDTO.getTitle())) {
            throw new CustomException(String.format(INVALID_PROJECT_ADD, projectDTO.getTitle()));
        }

        Project project = new Project();
        project.setTitle(projectDTO.getTitle());

        return project;
    }

    @Override
    public boolean isEmployeeInDB(String email) {
        Employee employee = employeeRepository.findEmployeeByEmail(email)
                .orElse(null);

        return employee != null;
    }

    @Override
    public boolean isProjectInDB(String title) {
        Project project = projectRepository.findProjectByTitle(title)
                .orElse(null);

        return project != null;
    }

    @Override
    public boolean isEmployeeInDB(Long id) {

        return employeeRepository.findById(id)
                .orElse(null) == null;
    }

    @Override
    public boolean isProjectInDB(Long id) {

        return projectRepository.findById(id)
                .orElse(null) == null;
    }

    @Override
    public boolean isAssignmentInDB(Long employeeId, Long projectId, Date dateFrom, Date dateTo) {
        //check whether assignment with these properties exist
        ProjectAssignment projectAssignment = projectAssignmentRepository.
                findProjectAssignmentByEmployeeIdAndProjectIdAndDateFromAndDateTo(
                        employeeId,
                        projectId,
                        dateFrom,
                        dateTo);

        return projectAssignment != null;
    }

    @Override
    @Transactional
    public void validateEmployeeDeletion(Long id) {
        //check whether employee exists in DB
        employeeRepository
                .findById(id)
                .orElseThrow(() ->
                        new CustomException(String.format(INVALID_EMPLOYEE, id)));
        //remove the associated assignments with this employee
        projectAssignmentRepository.deleteAllByEmployeeId(id);
        //remove the employee
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void validateProjectDeletion(Long id) {
        //check whether project exists in DB
        projectRepository
                .findById(id)
                .orElseThrow(() ->
                        new CustomException(String.format(INVALID_PROJECT_ID, id)));
        //remove associated assignments with this project
        projectAssignmentRepository.deleteAllByProjectId(id);
        //remove the project
        projectRepository.deleteById(id);
    }

    @Override
    public void validateAssignmentDeletion(Long id) {
        //check whether assignment exists in DB
        projectAssignmentRepository
                .findById(id)
                .orElseThrow(() ->
                        new CustomException(String.format(ASSIGNMENT_NOT_EXIST, id)));
        //remove the assignment
        projectAssignmentRepository.deleteById(id);
    }

    @Override
    public ProjectAssignment validateUpdateAssignment(Long id, ProjectAssignmentDTO projectAssignmentDTO) {
        ProjectAssignment updatedAssignment = projectAssignmentRepository.findById(id)
                .orElse(null);

        //check whether the updated employeeId exist
        if (isEmployeeInDB(projectAssignmentDTO.getEmployeeId())) {
            throw new CustomException(String.format(INVALID_EMPLOYEE, projectAssignmentDTO.getEmployeeId()));
        }

        //check whether the updated projectId exist
        if (isProjectInDB(projectAssignmentDTO.getProjectId())) {
            throw new CustomException(String.format(INVALID_PROJECT_ID, projectAssignmentDTO.getProjectId()));
        }

        assert updatedAssignment != null;
        Date updatedDateFrom = dateValidator.validateDate(projectAssignmentDTO.getDateFrom().toString());
        Date updatedDateTo = dateValidator.validateDate(projectAssignmentDTO.getDateTo().toString());

        if (updatedDateFrom.after(updatedDateTo)) {
            throw new CustomException(String.format(INVALID_DATE_TO, projectAssignmentDTO.getDateTo()));
        }

        //update assignment
        updatedAssignment.setEmployee(employeeRepository.findById(projectAssignmentDTO.getEmployeeId())
                .orElse(null));
        updatedAssignment.setProject(projectRepository.findById(projectAssignmentDTO.getProjectId())
                .orElse(null));
        updatedAssignment.setDateFrom(updatedDateFrom);
        updatedAssignment.setDateTo(updatedDateTo);

        return updatedAssignment;
    }
}

