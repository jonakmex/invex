package com.invex.port.api.viewmodel.response;

import com.invex.domain.usecase.response.Response;
import com.invex.port.api.viewmodel.model.EmployeeViewModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CreateEmployeeBatchVMResponse extends Response {
    private Flux<Long> employeeIds;
}
