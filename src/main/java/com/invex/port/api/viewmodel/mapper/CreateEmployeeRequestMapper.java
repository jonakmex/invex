package com.invex.port.api.viewmodel.mapper;

import com.invex.domain.usecase.request.CreateEmployeeRequest;
import com.invex.domain.usecase.request.UpdateEmployeeRequest;
import com.invex.port.api.viewmodel.request.CreateEmployeeVMRequest;
import com.invex.port.api.viewmodel.request.UpdateEmployeeVMRequest;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeRequestMapper implements ViewModelMapper <CreateEmployeeVMRequest, CreateEmployeeRequest> {

    @Override
    public CreateEmployeeRequest map(CreateEmployeeVMRequest createEmployeeVMRequest) {
        return CreateEmployeeRequest.builder()
                .name(createEmployeeVMRequest.getName())
                .surname(createEmployeeVMRequest.getSurname())
                .lastName(createEmployeeVMRequest.getLastName())
                .age(createEmployeeVMRequest.getAge())
                .gender(createEmployeeVMRequest.getGender())
                .birthDate(createEmployeeVMRequest.getBirthDate())
                .position(createEmployeeVMRequest.getPosition())
                .build();
    }
}
