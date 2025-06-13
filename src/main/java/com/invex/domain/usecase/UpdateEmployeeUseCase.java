package com.invex.domain.usecase;

import com.invex.domain.entity.Gender;
import com.invex.domain.usecase.model.EmployeeModel;
import com.invex.domain.usecase.request.Request;
import com.invex.domain.usecase.request.UpdateEmployeeRequest;
import com.invex.domain.usecase.response.Response;
import com.invex.domain.usecase.response.UpdateEmployeeResponse;
import com.invex.port.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class UpdateEmployeeUseCase extends UseCase {

    private EmployeeRepository employeeRepository;

    @Override
    protected Mono<Response> doExecute(Request request) {
        UpdateEmployeeRequest updateRequest = (UpdateEmployeeRequest) request;

        return employeeRepository.findById(updateRequest.getId())
                .flatMap(e -> {
                    e.setName(updateRequest.getName());
                    e.setSurname(updateRequest.getSurname());
                    e.setLastName(updateRequest.getLastName());
                    e.setAge(updateRequest.getAge());
                    e.setGender(Gender.valueOf(updateRequest.getGender()));
                    e.setBirthDate(updateRequest.getBirthDate());
                    e.setPosition(updateRequest.getPosition());
                    return employeeRepository.save(e);
                }).flatMap(e -> Mono.just(UpdateEmployeeResponse.builder()
                        .employeeModel(EmployeeModel.builder()
                                .id(e.getId())
                                .name(e.getName())
                                .surname(e.getSurname())
                                .lastName(e.getLastName())
                                .age(e.getAge())
                                .gender(e.getGender().toString())
                                .birthDate(e.getBirthDate())
                                .position(e.getPosition())
                                .build()).build()));

    }
}
