package com.invex.port.api.viewmodel.response;

import com.invex.port.api.viewmodel.model.EmployeeViewModel;
import lombok.Builder;
import lombok.Data;
import reactor.core.publisher.Flux;

@Data
@Builder
public class FindEmployeeByNameVMResponse {
    public Flux<EmployeeViewModel> employees;
}
