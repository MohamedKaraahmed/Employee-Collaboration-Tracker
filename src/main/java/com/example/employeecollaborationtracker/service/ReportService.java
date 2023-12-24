package com.example.employeecollaborationtracker.service;

import com.example.employeecollaborationtracker.dto.ReportDTO;

import java.util.List;

public interface ReportService {

    List<ReportDTO> getEmployeesOverlapReport();

}