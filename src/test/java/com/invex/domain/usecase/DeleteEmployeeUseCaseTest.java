package com.invex.domain.usecase;

import com.invex.port.repository.EmployeeRepository;
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
public class DeleteEmployeeUseCaseTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DeleteEmployeeUseCase deleteEmployeeUseCase;

    @Test
    void should_deleteEmployee() {
        when(employeeRepository.deleteById(anyLong())).thenReturn(Mono.empty());
        StepVerifier.create(deleteEmployeeUseCase.execute(
                com.invex.domain.usecase.request.DeleteEmployeeRequest.builder()
                        .employeeId(1L)
                        .build()
        ))
                .verifyComplete();
    }
}
