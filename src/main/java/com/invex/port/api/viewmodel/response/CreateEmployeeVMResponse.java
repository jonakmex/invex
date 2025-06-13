package com.invex.port.api.viewmodel.response;

import com.invex.domain.usecase.response.Response;
import com.invex.port.api.viewmodel.model.EmployeeViewModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CreateEmployeeVMResponse extends Response {
    private EmployeeViewModel employee;
}
