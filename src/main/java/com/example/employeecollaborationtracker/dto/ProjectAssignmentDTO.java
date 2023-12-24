package com.example.employeecollaborationtracker.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProjectAssignmentDTO {

    private Long employeeId;
    private Long projectId;
    private Date dateFrom;
    private Date dateTo;

}