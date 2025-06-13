package com.invex.domain.usecase;

import com.invex.domain.usecase.request.FindEmployeeByIdRequest;
import com.invex.domain.usecase.response.FindEmployeeByIdResponse;
import com.invex.port.repository.EmployeeRepository;
import com.invex.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FindEmployeeByIdUseCaseTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private FindEmployeeByIdUseCase findEmployeeByIdUseCase;

    @Test
    void should_findEmployeeById() {
        when(employeeRepository.findById(anyLong())).thenReturn(
                Mono.just(TestUtil.buildRandomEmployee())
        );
        StepVerifier.create(findEmployeeByIdUseCase.doExecute(FindEmployeeByIdRequest.builder()
                        .employeeId(1L)
                        .build()))
                .expectSubscription()
                .expectNextMatches(response -> {
                    FindEmployeeByIdResponse res = (FindEmployeeByIdResponse) response;
                    return res.getEmployee() != null &&
                            res.getEmployee().getId() != null &&
                            res.getEmployee().getName() != null &&
                            res.getEmployee().getSurname() != null &&
                            res.getEmployee().getLastName() != null &&
                            res.getEmployee().getAge() > 0 &&
                            res.getEmployee().getGender() != null &&
                            res.getEmployee().getBirthDate() != null &&
                            res.getEmployee().getPosition() != null;
                })
                .verifyComplete();
    }
}
