package com.invex.domain.usecase.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteEmployeeRequest extends Request {
    public Long employeeId;
}
