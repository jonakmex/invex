package com.invex.domain.usecase.response;

import com.invex.domain.usecase.model.EmployeeModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class FindEmployeeByNameResponse extends Response {
    public Flux<EmployeeModel> employees;
}
