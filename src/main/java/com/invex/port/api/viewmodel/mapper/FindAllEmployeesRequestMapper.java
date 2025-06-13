package com.invex.port.api.viewmodel.mapper;

import com.invex.domain.usecase.request.FindAllEmployeesRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;

@Service
public class FindAllEmployeesRequestMapper implements ViewModelMapper <ServerRequest, FindAllEmployeesRequest>{
    @Override
    public FindAllEmployeesRequest map(ServerRequest source) {
        return FindAllEmployeesRequest.builder().build();
    }
}
