package com.invex.port.api.handler;

import com.invex.domain.usecase.UseCase;
import com.invex.domain.usecase.request.*;
import com.invex.domain.usecase.response.*;
import com.invex.port.api.viewmodel.mapper.ViewModelMapper;
import com.invex.port.api.viewmodel.model.EmployeeViewModel;
import com.invex.port.api.viewmodel.request.CreateEmployeeBatchVMRequest;
import com.invex.port.api.viewmodel.request.CreateEmployeeVMRequest;
import com.invex.port.api.viewmodel.request.UpdateEmployeeVMRequest;
import com.invex.port.api.viewmodel.response.CreateEmployeeBatchVMResponse;
import com.invex.port.api.viewmodel.response.CreateEmployeeVMResponse;
import com.invex.port.api.viewmodel.response.FindAllEmployeesVMResponse;
import com.invex.port.api.viewmodel.response.UpdateEmployeeVMResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final UseCase findAllEmployeesUseCase;
    private final UseCase deleteEmployeeUseCase;
    private final UseCase updateEmployeeUseCase;
    private final UseCase createEmployeeUseCase;
    private final UseCase createEmployeeBatchUseCase;

    private final ViewModelMapper<ServerRequest,FindAllEmployeesRequest> findAllEmployeesRequestMapper;
    private final ViewModelMapper<Response, FindAllEmployeesVMResponse> findAllEmployeesResponseMapper;
    private final ViewModelMapper<UpdateEmployeeVMRequest, UpdateEmployeeRequest> updateEmployeeRequestMapper;
    private final ViewModelMapper<UpdateEmployeeResponse, UpdateEmployeeVMResponse> updateEmployeeResponseMapper;
    private final ViewModelMapper<CreateEmployeeVMRequest, CreateEmployeeRequest> createEmployeeRequestMapper;
    private final ViewModelMapper<CreateEmployeeResponse, CreateEmployeeVMResponse> createEmployeeResponseMapper;
    private final ViewModelMapper<CreateEmployeeBatchVMRequest, CreateEmployeeBatchRequest> createEmployeeBatchRequestMapper;
    private final ViewModelMapper<CreateEmployeeBatchResponse, CreateEmployeeBatchVMResponse> createEmployeeBatchResponseMapper;

    public Mono<ServerResponse> getAllEmployees(ServerRequest serverRequest) {
        return findAllEmployeesUseCase.execute(findAllEmployeesRequestMapper.map(serverRequest))
                .flatMap(response -> Mono.just(findAllEmployeesResponseMapper.map(response)))
                .flatMap(vm -> ServerResponse.ok().body(vm.getEmployees(), EmployeeViewModel.class));
    }

    public Mono<ServerResponse> deleteEmployee(ServerRequest serverRequest) {
        Long employeeId = Long.valueOf(serverRequest.pathVariable("id"));
        var request = DeleteEmployeeRequest.builder().employeeId(employeeId).build();
        return deleteEmployeeUseCase.execute(request)
                .then(ServerResponse.noContent()
                        .build());
    }

    public Mono<ServerResponse> updateEmployee(ServerRequest serverRequest) {
        Long employeeId = Long.valueOf(serverRequest.pathVariable("id"));
        return serverRequest.bodyToMono(UpdateEmployeeVMRequest.class)
                .flatMap(updateRequest -> {
                            UpdateEmployeeRequest updateEmployeeRequest = updateEmployeeRequestMapper.map(updateRequest);
                            updateEmployeeRequest.setId(employeeId);
                            return Mono.just(updateEmployeeRequest);
                        }
                )
                .flatMap(updateEmployeeUseCase::execute)
                .flatMap(response -> {
                    UpdateEmployeeResponse updateResponse = (UpdateEmployeeResponse) response;
                    return Mono.just(updateEmployeeResponseMapper.map(updateResponse));
                })
                .flatMap(updateEmployeeVMResponse -> ServerResponse.ok().bodyValue(updateEmployeeVMResponse));

    }

    public Mono<ServerResponse> createEmployee(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateEmployeeVMRequest.class)
                .flatMap(createRequest ->
                        Mono.just(createEmployeeRequestMapper.map(createRequest))
                )
                .flatMap(createEmployeeUseCase::execute)
                .flatMap(response -> {
                    CreateEmployeeResponse createResponse = (CreateEmployeeResponse) response;
                    return Mono.just(createEmployeeResponseMapper.map(createResponse));
                })
                .flatMap(createEmployeeVMResponse -> {
                    URI location = URI.create("/employees/" + createEmployeeVMResponse.getEmployee().getId());
                    return ServerResponse.created(location).bodyValue(createEmployeeVMResponse);
                });
    }

    public Mono<ServerResponse> createEmployeeBatch(ServerRequest serverRequest) {
        CreateEmployeeBatchVMRequest createBatchRequest = CreateEmployeeBatchVMRequest.builder()
                .employeeViewModelList(serverRequest.bodyToFlux(CreateEmployeeVMRequest.class))
                .build();
        return createEmployeeBatchUseCase.execute(createEmployeeBatchRequestMapper.map(createBatchRequest))
                .flatMap(response -> {
                    CreateEmployeeBatchResponse createResponse = (CreateEmployeeBatchResponse) response;
                    return Mono.just(createEmployeeBatchResponseMapper.map(createResponse));
                })
                .flatMap(response -> {
                    URI location = URI.create("/employees");
                    return ServerResponse.created(location).body(response.getEmployeeIds(), Long.class);
                });
    }

}
