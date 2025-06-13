package com.invex.domain.usecase.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CreateEmployeeBatchResponse extends Response {
    private Flux<Long> employeeIds;
}
