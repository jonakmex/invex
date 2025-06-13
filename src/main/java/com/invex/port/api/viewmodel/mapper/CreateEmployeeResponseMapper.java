package com.invex.port.api.viewmodel.mapper;

import com.invex.domain.usecase.model.EmployeeModel;
import com.invex.domain.usecase.response.CreateEmployeeResponse;
import com.invex.domain.usecase.response.UpdateEmployeeResponse;
import com.invex.port.api.viewmodel.model.EmployeeViewModel;
import com.invex.port.api.viewmodel.response.CreateEmployeeVMResponse;
import com.invex.port.api.viewmodel.response.UpdateEmployeeVMResponse;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeResponseMapper implements ViewModelMapper <CreateEmployeeResponse, CreateEmployeeVMResponse>{
    @Override
    public CreateEmployeeVMResponse map(CreateEmployeeResponse createResponse) {
        EmployeeModel employeeModel =
                createResponse.getEmployeeModel();
        return  CreateEmployeeVMResponse.builder()
                .employee(EmployeeViewModel.builder()
                        .id(employeeModel.getId())
                        .name(employeeModel.getName())
                        .surname(employeeModel.getSurname())
                        .lastName(employeeModel.getLastName())
                        .age(employeeModel.getAge())
                        .gender(employeeModel.getGender())
                        .birthDate(employeeModel.getBirthDate())
                        .position(employeeModel.getPosition())
                        .build())
                .build();
    }
}
