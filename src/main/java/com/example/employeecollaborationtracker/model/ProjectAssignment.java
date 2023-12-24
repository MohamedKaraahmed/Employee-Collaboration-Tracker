package com.example.employeecollaborationtracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.example.employeecollaborationtracker.util.common.ExceptionMessages.INVALID_ASSIGNMENT_EMPLOYEE;
import static com.example.employeecollaborationtracker.util.common.ExceptionMessages.INVALID_ASSIGNMENT_PROJECT;

@Entity
@Table(name = "project_assignments")
@Getter
@Setter
@NoArgsConstructor
public class ProjectAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = INVALID_ASSIGNMENT_EMPLOYEE)
    @ManyToOne(targetEntity = Employee.class, fetch = FetchType.EAGER)
    private Employee employee;

    @NotNull(message = INVALID_ASSIGNMENT_PROJECT)
    @ManyToOne(targetEntity = Project.class, fetch = FetchType.EAGER)
    private Project project;

    @DateTimeFormat
    private Date dateFrom;

    @DateTimeFormat
    private Date dateTo;

    private ProjectAssignment(Builder builder) {
        this.id = builder.id;
        this.employee = builder.employee;
        this.project = builder.project;
        this.dateFrom = builder.dateFrom;
        this.dateTo = builder.dateTo;
    }

    public static class Builder {
        private Long id;
        private Employee employee;
        private Project project;
        private Date dateFrom;
        private Date dateTo;

        public Builder employee(Employee employee) {
            this.employee = employee;
            return this;
        }

        public Builder project(Project project) {
            this.project = project;
            return this;
        }

        public Builder dateFrom(Date dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public Builder dateTo(Date dateTo) {
            this.dateTo = dateTo;
            return this;
        }

        public ProjectAssignment build() {
            return new ProjectAssignment(this);
        }
    }

}