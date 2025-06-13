package com.invex.domain.usecase.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class CreateEmployeeModel {
    @NotBlank
    @NotNull
    private String name;
    private String surname;
    private String lastName;
    @Positive
    private int age;
    @NotBlank
    @NotNull
    private String gender;
    @Past
    private LocalDate birthDate;
    private String position;
}
