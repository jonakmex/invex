package com.invex.port.api.viewmodel.mapper;

import com.invex.domain.usecase.response.FindAllEmployeesResponse;
import com.invex.domain.usecase.response.Response;
import com.invex.port.api.viewmodel.model.EmployeeViewModel;
import com.invex.port.api.viewmodel.response.FindAllEmployeesVMResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FindAllEmployeesResponseMapper implements ViewModelMapper <Response, FindAllEmployeesVMResponse>{
    @Override
    public FindAllEmployeesVMResponse map(Response useCaseResponse) {
        var response = (FindAllEmployeesResponse) useCaseResponse;
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
                        .build()));

        return FindAllEmployeesVMResponse.builder()
                .employees(employees)
                .build();
    }
}
