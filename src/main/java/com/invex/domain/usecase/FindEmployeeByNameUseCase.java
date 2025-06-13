package com.invex.domain.usecase;

import com.invex.domain.entity.Employee;
import com.invex.domain.usecase.model.EmployeeModel;
import com.invex.domain.usecase.request.FindEmployeeByNameRequest;
import com.invex.domain.usecase.request.Request;
import com.invex.domain.usecase.response.FindAllEmployeesResponse;
import com.invex.domain.usecase.response.FindEmployeeByNameResponse;
import com.invex.domain.usecase.response.Response;
import com.invex.port.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class FindEmployeeByNameUseCase extends UseCase {
    private final EmployeeRepository employeeRepository;

    @Override
    protected Mono<Response> doExecute(Request request) {
        FindEmployeeByNameRequest findEmployeeByNameRequest = (FindEmployeeByNameRequest) request;
        return Mono.just(FindEmployeeByNameResponse.builder()
                .employees(employeeRepository
                        .findByNameContaining(findEmployeeByNameRequest.getEmployeeName())
                        .flatMap(employee -> Mono.just(convertToModel(employee))))
                .build());
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
