package com.example.employeecollaborationtracker.service.Impl;

import com.example.employeecollaborationtracker.dto.ReportDTO;
import com.example.employeecollaborationtracker.repository.ProjectAssignmentRepository;
import com.example.employeecollaborationtracker.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final ProjectAssignmentRepository projectAssignmentRepository;

    @Autowired
    public ReportServiceImpl(ProjectAssignmentRepository projectAssignmentRepository) {
        this.projectAssignmentRepository = projectAssignmentRepository;
    }


    @Override
    public List<ReportDTO> getEmployeesOverlapReport() {
        List<Object[]> results = projectAssignmentRepository.employeeOverlapReport();
        List<ReportDTO> reportList = new ArrayList<>();
        //map overlap result to ReportDTO
        for (Object[] result : results) {
            Long employee1Id = ((Number) result[0]).longValue();
            Long employee2Id = ((Number) result[1]).longValue();
            Long projectId = ((Number) result[2]).longValue();
            Long daysWorkedTogether = ((Number) result[3]).longValue();

            reportList.add(new ReportDTO(employee1Id, employee2Id, projectId, daysWorkedTogether));
        }

        return reportList;
    }
}

