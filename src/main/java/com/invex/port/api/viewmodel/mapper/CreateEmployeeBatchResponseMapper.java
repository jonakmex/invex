package com.invex.port.api.viewmodel.mapper;

import com.invex.domain.usecase.response.CreateEmployeeBatchResponse;
import com.invex.port.api.viewmodel.response.CreateEmployeeBatchVMResponse;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeBatchResponseMapper implements ViewModelMapper <CreateEmployeeBatchResponse, CreateEmployeeBatchVMResponse>{
    @Override
    public CreateEmployeeBatchVMResponse map(CreateEmployeeBatchResponse createResponse) {

        return  CreateEmployeeBatchVMResponse.builder()
                .employeeIds(createResponse.getEmployeeIds())
                .build();
    }
}
