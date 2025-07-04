package com.invex.domain.usecase.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class UpdateEmployeeRequest extends Request {

    @NotNull
    private Long id;
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
    private boolean active;
}
