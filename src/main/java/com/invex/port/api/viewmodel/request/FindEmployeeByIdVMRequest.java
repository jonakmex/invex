package com.invex.port.api.viewmodel.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindEmployeeByIdVMRequest {
    private Long employeeId;
}
