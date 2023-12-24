package com.example.employeecollaborationtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReportDTO {

    private Long employeeId1;
    private Long employeeId2;
    private Long projectId;
    private Long togetherWorkingDaysCount;

}