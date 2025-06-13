package com.invex.port.api.handler;

import com.invex.domain.usecase.UseCase;
import com.invex.domain.usecase.model.EmployeeModel;
import com.invex.domain.usecase.request.*;
import com.invex.domain.usecase.response.CreateEmployeeResponse;
import com.invex.domain.usecase.response.FindAllEmployeesResponse;
import com.invex.domain.usecase.response.Response;
import com.invex.domain.usecase.response.UpdateEmployeeResponse;
import com.invex.port.api.viewmodel.model.EmployeeViewModel;
import com.invex.port.api.viewmodel.request.CreateEmployeeVMRequest;
import com.invex.port.api.viewmodel.request.UpdateEmployeeVMRequest;
import com.invex.port.api.viewmodel.response.CreateEmployeeVMResponse;
import com.invex.port.api.viewmodel.response.FindAllEmployeesVMResponse;
import com.invex.port.api.viewmodel.response.UpdateEmployeeVMResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
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

    public Mono<ServerResponse> getAllEmployees(ServerRequest serverRequest) {
        var request = FindAllEmployeesRequest.builder().build();
        return findAllEmployeesUseCase.execute(request)
                .flatMap(response -> Mono.just(mapToView(response)))
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
                .flatMap(updateRequest ->
                        Mono.just(UpdateEmployeeRequest.builder()
                                .id(employeeId)
                                .name(updateRequest.getName())
                                .surname(updateRequest.getSurname())
                                .lastName(updateRequest.getLastName())
                                .age(updateRequest.getAge())
                                .gender(updateRequest.getGender())
                                .birthDate(updateRequest.getBirthDate())
                                .position(updateRequest.getPosition())
                                .build())
                )
                .flatMap(updateEmployeeUseCase::execute)
                .flatMap(response -> {
                    UpdateEmployeeResponse updateResponse = (UpdateEmployeeResponse) response;
                    EmployeeModel employeeModel = updateResponse.getEmployeeModel();
                    UpdateEmployeeVMResponse vmResponse = UpdateEmployeeVMResponse.builder()
                            .employee(EmployeeViewModel.builder()
                                    .id(employeeModel.getId())
                                    .name(employeeModel.getName())
                                    .surname(employeeModel.getSurname())
                                    .lastName(employeeModel.getLastName())
                                    .age(employeeModel.getAge())
                                    .gender(employeeModel.getGender())
                                    .birthDate(employeeModel.getBirthDate())
                                    .position(employeeModel.getPosition())
                                    .build())
                            .build();
                    return Mono.just(vmResponse);
                })
                .flatMap(updateEmployeeVMResponse -> ServerResponse.ok().bodyValue(updateEmployeeVMResponse));

    }

    public Mono<ServerResponse> createEmployee(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateEmployeeVMRequest.class)
                .flatMap(createRequest ->
                        Mono.just(CreateEmployeeRequest.builder()
                                .name(createRequest.getName())
                                .surname(createRequest.getSurname())
                                .lastName(createRequest.getLastName())
                                .age(createRequest.getAge())
                                .gender(createRequest.getGender())
                                .birthDate(createRequest.getBirthDate())
                                .position(createRequest.getPosition())
                                .build())
                )
                .flatMap(createEmployeeUseCase::execute)
                .flatMap(response -> {
                    CreateEmployeeResponse createResponse = (CreateEmployeeResponse) response;
                    EmployeeModel employeeModel = createResponse.getEmployeeModel();
                    CreateEmployeeVMResponse vmResponse = CreateEmployeeVMResponse.builder()
                            .employee(EmployeeViewModel.builder()
                                    .id(employeeModel.getId())
                                    .name(employeeModel.getName())
                                    .surname(employeeModel.getSurname())
                                    .lastName(employeeModel.getLastName())
                                    .age(employeeModel.getAge())
                                    .gender(employeeModel.getGender())
                                    .birthDate(employeeModel.getBirthDate())
                                    .position(employeeModel.getPosition())
                                    .build())
                            .build();
                    return Mono.just(vmResponse);
                })
                .flatMap(createEmployeeVMResponse -> {
                    URI location = URI.create("/employees/" + createEmployeeVMResponse.getEmployee().getId());
                    return ServerResponse.created(location).bodyValue(createEmployeeVMResponse);
                });

    }

    private FindAllEmployeesVMResponse mapToView(Response useCaseResponse) {
        FindAllEmployeesResponse response = (FindAllEmployeesResponse) useCaseResponse;
        Flux<EmployeeViewModel> employees = response.getEmployees()
                .flatMap(employeeModel -> Mono.just(EmployeeViewModel.builder()
                        .id(employeeModel.getId())
                        .name(employeeModel.getName())
                        .surname(employeeModel.getSurname())
                        .lastName(employeeModel.getLastName())
                        .age(employeeModel.getAge())
                        .gender(employeeModel.getGender())
                        .birthDate(employeeModel.getBirthDate())
                        .position(employeeModel.getPosition())
                        .build()));

        return FindAllEmployeesVMResponse.builder()
                .employees(employees)
                .build();

    }
}
