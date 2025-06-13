package com.invex.domain.usecase;

import com.invex.domain.entity.Employee;
import com.invex.domain.entity.Gender;
import com.invex.domain.usecase.request.CreateEmployeeBatchRequest;
import com.invex.domain.usecase.request.CreateEmployeeRequest;
import com.invex.domain.usecase.response.CreateEmployeeBatchResponse;
import com.invex.domain.usecase.response.CreateEmployeeResponse;
import com.invex.port.repository.EmployeeRepository;
import com.invex.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CreateEmployeeBatchUseCaseTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private CreateEmployeeBatchUseCase createEmployeeBatchUseCase;

    @Test
    void should_createEmployees() {


        when(employeeRepository.save(any())).thenAnswer(invocation -> {
            Employee employee = invocation.getArgument(0);
            employee.setId(new Random().nextLong()); // Assign the ID
            return Mono.just(employee);
        });

        CreateEmployeeBatchRequest request = CreateEmployeeBatchRequest.builder()
                .employees(Flux.fromIterable(TestUtil.buildRandomEmployeesModel(10)))
                .build();

        StepVerifier.create(createEmployeeBatchUseCase.execute(request))
                .expectSubscription()
                .expectNextMatches(response -> {
                    CreateEmployeeBatchResponse res = (CreateEmployeeBatchResponse) response;
                    assertNotNull(res.getEmployeeIds());
                    return res.getEmployeeIds().collectList()
                            .block()
                            .size() == 10;
                })
                .verifyComplete();
    }
}
