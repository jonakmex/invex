package com.invex.port.api.viewmodel.mapper;

import com.invex.domain.usecase.model.EmployeeModel;
import com.invex.domain.usecase.response.FindEmployeeByIdResponse;
import com.invex.domain.usecase.response.Response;
import com.invex.port.api.viewmodel.model.EmployeeViewModel;
import com.invex.port.api.viewmodel.response.FindEmployeeByIdVMResponse;
import org.springframework.stereotype.Service;

@Service
public class FindEmployeeByIdResponseMapper implements ViewModelMapper <Response, FindEmployeeByIdVMResponse>{
    @Override
    public FindEmployeeByIdVMResponse map(Response useCaseResponse) {
        var responseUseCase = (FindEmployeeByIdResponse) useCaseResponse;
        EmployeeModel employee = responseUseCase.getEmployee();

        return FindEmployeeByIdVMResponse.builder()
                .employee(EmployeeViewModel.builder()
                        .id(employee.getId())
                        .name(employee.getName())
                        .surname(employee.getSurname())
                        .lastName(employee.getLastName())
                        .age(employee.getAge())
                        .gender(employee.getGender())
                        .birthDate(employee.getBirthDate())
                        .position(employee.getPosition())
                        .createdAt(employee.getCreatedAt())
                        .active(employee.isActive())
                        .build())
                .build();
    }
}
