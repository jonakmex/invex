package com.invex.port.api.viewmodel.mapper;

import com.invex.domain.usecase.request.UpdateEmployeeRequest;
import com.invex.port.api.viewmodel.request.UpdateEmployeeVMRequest;
import org.springframework.stereotype.Service;

@Service
public class UpdateEmployeeRequestMapper implements ViewModelMapper <UpdateEmployeeVMRequest, UpdateEmployeeRequest> {

    @Override
    public UpdateEmployeeRequest map(UpdateEmployeeVMRequest updateEmployeeVMRequest) {
        return UpdateEmployeeRequest.builder()
                .name(updateEmployeeVMRequest.getName())
                .surname(updateEmployeeVMRequest.getSurname())
                .lastName(updateEmployeeVMRequest.getLastName())
                .age(updateEmployeeVMRequest.getAge())
                .gender(updateEmployeeVMRequest.getGender())
                .birthDate(updateEmployeeVMRequest.getBirthDate())
                .position(updateEmployeeVMRequest.getPosition())
                .active(updateEmployeeVMRequest.isActive())
                .build();
    }
}
