package com.invex.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;


@Data
@Builder
public class Employee {
    @Id
    private Long id;
    private String name;
    private String surname;
    private String lastName;
    private int age;
    private Gender gender;
    private LocalDate birthDate;
    private String position;
}
