package com.invex.port.api.viewmodel.mapper;

import com.invex.domain.usecase.request.FindEmployeeByNameRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;

@Service
public class FindEmployeeByNameRequestMapper implements ViewModelMapper <ServerRequest, FindEmployeeByNameRequest>{
    @Override
    public FindEmployeeByNameRequest map(ServerRequest serverRequest) {
        String name = serverRequest.queryParam("name").orElse("");
        return FindEmployeeByNameRequest.builder()
                .employeeName(name)
                .build();
    }
}
