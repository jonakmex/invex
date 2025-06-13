package com.invex.domain.usecase;

import com.invex.domain.usecase.request.DeleteEmployeeRequest;
import com.invex.domain.usecase.request.Request;
import com.invex.domain.usecase.response.DeleteEmployeeResponse;
import com.invex.domain.usecase.response.Response;
import com.invex.port.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class DeleteEmployeeUseCase extends UseCase {
    private final EmployeeRepository employeeRepository;


    @Override
    public Mono<Response> doExecute(Request request) {
        if (!(request instanceof com.invex.domain.usecase.request.DeleteEmployeeRequest)) {
            return Mono.error(new IllegalArgumentException("Invalid request type"));
        }
        DeleteEmployeeRequest deleteEmployeeRequest = (DeleteEmployeeRequest) request;

        return employeeRepository.deleteById(deleteEmployeeRequest.employeeId)
                .flatMap(r -> Mono.just(DeleteEmployeeResponse.builder().build()));
    }
}
