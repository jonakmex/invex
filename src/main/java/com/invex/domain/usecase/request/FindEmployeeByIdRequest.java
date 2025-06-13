package com.invex.domain.usecase.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class FindEmployeeByIdRequest extends Request {
    private Long employeeId;
}
