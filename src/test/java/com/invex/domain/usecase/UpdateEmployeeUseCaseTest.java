package com.invex.domain.usecase;

import com.invex.domain.entity.Employee;
import com.invex.domain.entity.Gender;
import com.invex.domain.usecase.request.UpdateEmployeeRequest;
import com.invex.domain.usecase.response.UpdateEmployeeResponse;
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
public class UpdateEmployeeUseCaseTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private UpdateEmployeeUseCase updateEmployeeUseCase;

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
        when(employeeRepository.findById(1L)).thenReturn(Mono.just(randomEmployee));
        when(employeeRepository.save(any())).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));
        UpdateEmployeeRequest request = UpdateEmployeeRequest.builder()
                .id(1L)
                .name("John")
                .surname("Doe")
                .lastName("Smith")
                .age(30)
                .gender("MALE")
                .birthDate(LocalDate.of(1993, 5, 15))
                .position("Developer")
                .build();

        StepVerifier.create(updateEmployeeUseCase.doExecute(request))
                .expectSubscription()
                .expectNextMatches(response -> {
                    UpdateEmployeeResponse res = (UpdateEmployeeResponse) response;
                    assertNotNull(res.getEmployeeModel());
                    return res.getEmployeeModel().getId() != null;
                })
                .verifyComplete();
    }
}
