package com.invex.domain.usecase.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UpdateEmployeeRequest extends Request {
    private Long id;
    private String name;
    private String surname;
    private String lastName;
    private int age;
    private String gender;
    private LocalDate birthDate;
    private String position;
}
