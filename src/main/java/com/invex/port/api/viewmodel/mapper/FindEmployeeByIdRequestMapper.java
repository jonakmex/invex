package com.invex.port.api.viewmodel.mapper;

import com.invex.domain.usecase.request.FindEmployeeByIdRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;

@Service
public class FindEmployeeByIdRequestMapper implements ViewModelMapper <ServerRequest, FindEmployeeByIdRequest>{
    @Override
    public FindEmployeeByIdRequest map(ServerRequest serverRequest) {
        return FindEmployeeByIdRequest.builder()
                .employeeId(Long.valueOf(serverRequest.pathVariable("id")))
                .build();
    }
}
