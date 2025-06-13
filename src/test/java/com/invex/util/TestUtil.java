package com.invex.util;

import com.invex.domain.entity.Employee;
import com.invex.domain.entity.Gender;
import com.invex.domain.usecase.request.CreateEmployeeRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TestUtil {

    public static List<Employee> buildRandomEmployees(int count) {
        List<Employee> employees = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= count; i++) {
            LocalDate start = LocalDate.now().minusYears(50);
            long days = 50 * 365; // Approximate days in 50 years
            long randomDay = new Random().nextLong(days + 1);
            employees.add(Employee.builder()
                    .id((long) i)
                    .name("Name" + i)
                    .surname("Surname" + i)
                    .lastName("LastName" + i)
                    .age(20 + random.nextInt(30))
                    .gender(random.nextBoolean() ? Gender.MALE : Gender.FEMALE)
                    .birthDate(start.plusDays(randomDay))
                    .position("Position-" + UUID.randomUUID().toString().substring(0, 5))
                    .build());
        }
        return employees;
    }

    public static List<CreateEmployeeRequest> buildRandomEmployeesModel(int count) {
        List<CreateEmployeeRequest> employees = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= count; i++) {
            LocalDate start = LocalDate.now().minusYears(50);
            long days = 50 * 365; // Approximate days in 50 years
            long randomDay = new Random().nextLong(days + 1);
            employees.add(CreateEmployeeRequest.builder()
                    .name("Name" + i)
                    .surname("Surname" + i)
                    .lastName("LastName" + i)
                    .age(20 + random.nextInt(30))
                    .gender((random.nextBoolean() ? Gender.MALE : Gender.FEMALE).toString())
                    .birthDate(start.plusDays(randomDay))
                    .position("Position-" + UUID.randomUUID().toString().substring(0, 5))
                    .build());
        }
        return employees;
    }
}
