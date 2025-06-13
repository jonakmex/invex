package com.invex.port.api.viewmodel.mapper;

import com.invex.domain.usecase.request.CreateEmployeeBatchRequest;
import com.invex.domain.usecase.request.CreateEmployeeRequest;
import com.invex.port.api.viewmodel.request.CreateEmployeeBatchVMRequest;
import com.invex.port.api.viewmodel.request.CreateEmployeeVMRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CreateEmployeeBatchRequestMapper implements ViewModelMapper <CreateEmployeeBatchVMRequest, CreateEmployeeBatchRequest> {
    private final ViewModelMapper<CreateEmployeeVMRequest, CreateEmployeeRequest> createEmployeeRequestMapper;

    @Override
    public CreateEmployeeBatchRequest map(CreateEmployeeBatchVMRequest createEmployeeBatchVMRequest) {

        return CreateEmployeeBatchRequest.builder()
                .employees(createEmployeeBatchVMRequest.getEmployeeViewModelList()
                        .flatMap(vm -> Mono.just(createEmployeeRequestMapper.map(vm))))
                .build();
    }
}
