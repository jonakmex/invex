package com.invex.domain.usecase;

import com.invex.domain.entity.Employee;
import com.invex.domain.entity.Gender;
import com.invex.domain.usecase.request.CreateEmployeeBatchRequest;
import com.invex.domain.usecase.request.Request;
import com.invex.domain.usecase.response.CreateEmployeeBatchResponse;
import com.invex.domain.usecase.response.Response;
import com.invex.port.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CreateEmployeeBatchUseCase extends UseCase {

    private EmployeeRepository employeeRepository;

    @Override
    protected Mono<Response> doExecute(Request request) {
        CreateEmployeeBatchRequest createEmployeeBatchRequest = (CreateEmployeeBatchRequest) request;
        return Mono.just(CreateEmployeeBatchResponse.builder()
                .employeeIds(createEmployeeBatchRequest.getEmployees()
                        .flatMap(e -> Mono.just(Employee.builder()
                                .name(e.getName())
                                .surname(e.getSurname())
                                .lastName(e.getLastName())
                                .age(e.getAge())
                                .gender(Gender.valueOf(e.getGender()))
                                .birthDate(e.getBirthDate())
                                .position(e.getPosition())
                                .active(e.isActive())
                                .build()))
                        .flatMap(employeeRepository::save)
                        .flatMap(e -> Mono.just(e.getId())))
                .build());
    }
}
