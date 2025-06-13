package com.invex.domain.usecase;

import com.invex.domain.entity.Employee;
import com.invex.domain.entity.Gender;
import com.invex.domain.usecase.model.EmployeeModel;
import com.invex.domain.usecase.request.CreateEmployeeRequest;
import com.invex.domain.usecase.request.Request;
import com.invex.domain.usecase.response.CreateEmployeeResponse;
import com.invex.domain.usecase.response.Response;
import com.invex.port.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CreateEmployeeUseCase extends UseCase {

    private EmployeeRepository employeeRepository;

    @Override
    protected Mono<Response> doExecute(Request request) {
        CreateEmployeeRequest createEmployeeRequest = (CreateEmployeeRequest) request;
        Employee employee = Employee.builder()
                .name(createEmployeeRequest.getName())
                .surname(createEmployeeRequest.getSurname())
                .lastName(createEmployeeRequest.getLastName())
                .age(createEmployeeRequest.getAge())
                .gender(Gender.valueOf(createEmployeeRequest.getGender()))
                .birthDate(createEmployeeRequest.getBirthDate())
                .position(createEmployeeRequest.getPosition())
                .active(createEmployeeRequest.isActive())
                .build();
        return employeeRepository.save(employee)
                .flatMap(e -> Mono.just(CreateEmployeeResponse.builder()
                        .employeeModel(EmployeeModel.builder()
                                .id(e.getId())
                                .name(e.getName())
                                .surname(e.getSurname())
                                .lastName(e.getLastName())
                                .age(e.getAge())
                                .gender(e.getGender().toString())
                                .birthDate(e.getBirthDate())
                                .position(e.getPosition())
                                .active(e.isActive())
                                .createdAt(e.getCreatedAt())
                                .build()).build()));

    }
}
