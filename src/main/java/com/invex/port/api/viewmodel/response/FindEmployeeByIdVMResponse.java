package com.invex.port.api.viewmodel.response;

import com.invex.port.api.viewmodel.model.EmployeeViewModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindEmployeeByIdVMResponse {
    public EmployeeViewModel employee;
}
