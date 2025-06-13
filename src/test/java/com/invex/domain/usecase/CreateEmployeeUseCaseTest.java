package com.invex.domain.usecase;

import com.invex.domain.entity.Employee;
import com.invex.domain.entity.Gender;
import com.invex.domain.usecase.request.CreateEmployeeRequest;
import com.invex.domain.usecase.response.CreateEmployeeResponse;
import com.invex.port.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CreateEmployeeUseCaseTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private CreateEmployeeUseCase createEmployeeUseCase;

    @Test
    void should_updateEmployee() {
        Employee randomEmployee = Employee.builder()
                .id(1L)
                .name("RandomName")
                .surname("RandomSurname")
                .lastName("RandomLastName")
                .age(28)
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(1995, 3, 10))
                .position("Engineer")
                .build();

        when(employeeRepository.save(any())).thenAnswer(invocation -> {
            Employee employee = invocation.getArgument(0);
            employee.setId(1L); // Assign the ID
            return Mono.just(employee);
        });

        CreateEmployeeRequest request = CreateEmployeeRequest.builder()
                .name("John")
                .surname("Doe")
                .lastName("Smith")
                .age(30)
                .gender("MALE")
                .birthDate(LocalDate.of(1993, 5, 15))
                .position("Developer")
                .build();

        StepVerifier.create(createEmployeeUseCase.execute(request))
                .expectSubscription()
                .expectNextMatches(response -> {
                    CreateEmployeeResponse res = (CreateEmployeeResponse) response;
                    assertNotNull(res.getEmployeeModel());
                    return res.getEmployeeModel().getId() != null;
                })
                .verifyComplete();
    }
}
