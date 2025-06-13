package com.invex.domain.usecase;

import com.invex.domain.usecase.request.FindEmployeeByNameRequest;
import com.invex.domain.usecase.response.FindAllEmployeesResponse;
import com.invex.domain.usecase.response.FindEmployeeByNameResponse;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FindEmployeeByNameUseCaseTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private FindEmployeeByNameUseCase findEmployeeByNameUseCase;

    @Test
    void should_findEmployeeById() {
        when(employeeRepository.findByNameContaining(anyString())).thenReturn(
                Flux.fromIterable(TestUtil.buildRandomEmployees(10))
        );

        StepVerifier.create(findEmployeeByNameUseCase.doExecute(FindEmployeeByNameRequest.builder()
                        .employeeName("John")
                        .build()))
                .expectSubscription()
                .expectNextMatches(response -> {
                    FindEmployeeByNameResponse res = (FindEmployeeByNameResponse) response;
                    return res.getEmployees().collectList().block().size() == 10;
                })
                .verifyComplete();
    }
}
