package com.invex.port.api.viewmodel.request;

import lombok.Builder;
import lombok.Data;
import reactor.core.publisher.Flux;

@Data
@Builder
public class CreateEmployeeBatchVMRequest {
    private Flux<CreateEmployeeVMRequest> employeeViewModelList;
}
