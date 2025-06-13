package com.invex.port.api.viewmodel.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EmployeeViewModel {
    private Long id;
    private String name;
    private String surname;
    private String lastName;
    private int age;
    private String gender;
    private LocalDate birthDate;
    private String position;
    private LocalDate createdAt;
    private boolean active;
}
