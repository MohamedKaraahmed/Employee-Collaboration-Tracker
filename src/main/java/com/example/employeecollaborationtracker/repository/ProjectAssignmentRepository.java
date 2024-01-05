package com.example.employeecollaborationtracker.repository;

import com.example.employeecollaborationtracker.model.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Long> {
    ProjectAssignment findProjectAssignmentByEmployeeIdAndProjectIdAndDateFromAndDateTo
            (Long employeeId, Long projectId, Date dateFrom, Date dateTo);

    @Query(value = """
            SELECT
                pa1.employee_id AS employee1Id,
                pa2.employee_id AS employee2Id,
                pa1.project_id AS projectId,
                DATEDIFF(
                    LEAST(COALESCE(pa1.date_to, CURDATE()), COALESCE(pa2.date_to, CURDATE())),
                    GREATEST(pa1.date_from, pa2.date_from)
                ) + 1 AS daysWorkedTogether
            FROM
                project_assignments pa1
            JOIN
                project_assignments pa2 ON pa1.project_id = pa2.project_id AND pa1.employee_id < pa2.employee_id
            WHERE
                pa1.date_from <= COALESCE(pa2.date_to, CURDATE())
                AND pa2.date_from <= COALESCE(pa1.date_to, CURDATE())
                AND GREATEST(pa1.date_from, pa2.date_from) <= LEAST(COALESCE(pa1.date_to, CURDATE()), COALESCE(pa2.date_to, CURDATE()))""",
            nativeQuery = true)
    List<Object[]> employeeOverlapReport();

    void deleteAllByEmployeeId(Long id);
    void deleteAllByProjectId(Long id);
}
