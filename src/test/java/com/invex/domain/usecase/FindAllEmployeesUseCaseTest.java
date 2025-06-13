package com.invex.domain.usecase;

import com.invex.domain.usecase.request.FindAllEmployeesRequest;
import com.invex.domain.usecase.response.FindAllEmployeesResponse;
import com.invex.port.repository.EmployeeRepository;
import com.invex.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FindAllEmployeesUseCaseTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private FindAllEmployeesUseCase findAllEmployeesUseCase;

    @Test
    void should_findAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(
                Flux.fromIterable(TestUtil.buildRandomEmployees(10))
        );
        StepVerifier.create(findAllEmployeesUseCase.doExecute(FindAllEmployeesRequest.builder().build()))
                .expectSubscription()
                .expectNextMatches(response -> {
                    FindAllEmployeesResponse res = (FindAllEmployeesResponse) response;
                    return res.getEmployees().collectList().block().size() == 10;
                })
                .verifyComplete();
    }
}
