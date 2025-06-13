package com.invex.port.api.viewmodel.mapper;

import com.invex.domain.usecase.model.EmployeeModel;
import com.invex.domain.usecase.response.UpdateEmployeeResponse;
import com.invex.port.api.viewmodel.model.EmployeeViewModel;
import com.invex.port.api.viewmodel.response.UpdateEmployeeVMResponse;
import org.springframework.stereotype.Service;

@Service
public class UpdateEmployeeResponseMapper implements ViewModelMapper <UpdateEmployeeResponse, UpdateEmployeeVMResponse>{
    @Override
    public UpdateEmployeeVMResponse map(UpdateEmployeeResponse updateResponse) {
        EmployeeModel employeeModel = updateResponse.getEmployeeModel();
        return  UpdateEmployeeVMResponse.builder()
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
