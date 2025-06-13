package com.invex.port.api.viewmodel.mapper;

import com.invex.domain.usecase.response.FindEmployeeByNameResponse;
import com.invex.domain.usecase.response.Response;
import com.invex.port.api.viewmodel.model.EmployeeViewModel;
import com.invex.port.api.viewmodel.response.FindEmployeeByNameVMResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FindEmployeeByNameResponseMapper implements ViewModelMapper <Response, FindEmployeeByNameVMResponse>{
    @Override
    public FindEmployeeByNameVMResponse map(Response useCaseResponse) {
        var response = (FindEmployeeByNameResponse) useCaseResponse;
        Flux<EmployeeViewModel> employees = response.getEmployees()
                .flatMap(employeeModel -> Mono.just(EmployeeViewModel.builder()
                        .id(employeeModel.getId())
                        .name(employeeModel.getName())
                        .surname(employeeModel.getSurname())
                        .lastName(employeeModel.getLastName())
                        .age(employeeModel.getAge())
                        .gender(employeeModel.getGender())
                        .birthDate(employeeModel.getBirthDate())
                        .position(employeeModel.getPosition())
                        .createdAt(employeeModel.getCreatedAt())
                        .active(employeeModel.isActive())
                        .build()));

        return FindEmployeeByNameVMResponse.builder()
                .employees(employees)
                .build();
    }
}
