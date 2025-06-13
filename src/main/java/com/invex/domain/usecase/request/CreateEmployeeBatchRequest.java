package com.invex.domain.usecase.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CreateEmployeeBatchRequest extends Request {
    Flux<CreateEmployeeRequest> employees;
}
