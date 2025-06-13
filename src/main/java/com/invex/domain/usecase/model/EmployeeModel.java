package com.invex.domain.usecase.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class EmployeeModel {
    private Long id;
    private String name;
    private String surname;
    private String lastName;
    private int age;
    private String gender;
    private LocalDate birthDate;
    private String position;
}
