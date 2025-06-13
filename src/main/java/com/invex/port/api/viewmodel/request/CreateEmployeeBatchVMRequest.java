package com.invex.port.api.viewmodel.request;

import lombok.Builder;
import lombok.Data;
import reactor.core.publisher.Flux;

import java.util.List;

@Data
@Builder
public class CreateEmployeeBatchVMRequest {
    private Flux<CreateEmployeeVMRequest> employeeViewModelList;
}
