package com.invex.port.api.viewmodel.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UpdateEmployeeVMRequest  {
    private String name;
    private String surname;
    private String lastName;
    private int age;
    private String gender;
    private LocalDate birthDate;
    private String position;
    private boolean active;
}
