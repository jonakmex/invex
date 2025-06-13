package com.invex.domain.usecase.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CreateEmployeeRequest extends Request {
    private String name;
    private String surname;
    private String lastName;
    private int age;
    private String gender;
    private LocalDate birthDate;
    private String position;
}
