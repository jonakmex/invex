package com.invex.domain.usecase;

import com.invex.domain.entity.Employee;
import com.invex.domain.usecase.model.EmployeeModel;
import com.invex.domain.usecase.request.FindEmployeeByIdRequest;
import com.invex.domain.usecase.request.Request;
import com.invex.domain.usecase.response.FindEmployeeByIdResponse;
import com.invex.domain.usecase.response.Response;
import com.invex.port.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class FindEmployeeByIdUseCase extends UseCase {
    private final EmployeeRepository employeeRepository;

    @Override
    public Mono<Response> doExecute(Request request) {
        var findEmployeeByIdRequest = (FindEmployeeByIdRequest) request;
        return employeeRepository
                .findById(findEmployeeByIdRequest.getEmployeeId())
                .flatMap(employee -> Mono.just(convertToModel(employee)))
                .flatMap(employeeModel -> Mono.just(FindEmployeeByIdResponse.builder()
                        .employee(employeeModel)
                        .build()));
    }

    private EmployeeModel convertToModel(Employee employee) {
        return EmployeeModel.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .lastName(employee.getLastName())
                .age(employee.getAge())
                .gender(employee.getGender().toString())
                .birthDate(employee.getBirthDate())
                .position(employee.getPosition())
                .createdAt(employee.getCreatedAt())
                .active(employee.isActive())
                .build();
    }
}
