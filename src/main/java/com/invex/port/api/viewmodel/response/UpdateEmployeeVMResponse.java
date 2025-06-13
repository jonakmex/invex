package com.invex.port.api.viewmodel.response;

import com.invex.domain.usecase.response.Response;
import com.invex.port.api.viewmodel.model.EmployeeViewModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateEmployeeVMResponse extends Response {
    private EmployeeViewModel employee;
}
