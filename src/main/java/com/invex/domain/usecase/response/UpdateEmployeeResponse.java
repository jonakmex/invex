package com.invex.domain.usecase.response;

import com.invex.domain.usecase.model.EmployeeModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class UpdateEmployeeResponse extends Response {
    private EmployeeModel employeeModel;
}
