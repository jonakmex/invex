package com.invex.domain.usecase;

import com.invex.domain.entity.Employee;
import com.invex.domain.usecase.model.EmployeeModel;
import com.invex.domain.usecase.request.Request;
import com.invex.domain.usecase.response.FindAllEmployeesResponse;
import com.invex.domain.usecase.response.Response;
import com.invex.port.repository.EmployeeRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Builder
@RequiredArgsConstructor
public class FindAllEmployeesUseCase extends UseCase {

    private final EmployeeRepository employeeRepository;

    @Override
    public Mono<Response> doExecute(Request request) {
        return Mono.just(FindAllEmployeesResponse.builder()
                .employees(employeeRepository
                        .findAll()
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
                .build();
    }

}
