package com.example.employeecollaborationtracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.example.employeecollaborationtracker.util.common.ExceptionMessages.EMPTY_EMAIL;
import static com.example.employeecollaborationtracker.util.common.ExceptionMessages.INVALID_EMAIL;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Email(message = INVALID_EMAIL)
    @NotBlank(message = EMPTY_EMAIL)
    private String email;

}