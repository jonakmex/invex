package com.invex.domain.usecase;

import com.invex.domain.usecase.request.Request;
import com.invex.domain.usecase.response.Response;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import reactor.core.publisher.Mono;

import java.util.Set;

public abstract class UseCase {
    protected Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    protected abstract Mono<Response> doExecute(Request request);

    public Mono<Response> execute(Request request) {
        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        if (violations.isEmpty()) {
            return doExecute(request);
        }
        return Mono.error(new IllegalArgumentException("Validation failed: " + violations.stream()
                .map(ConstraintViolation::getMessage)
                .reduce((msg1, msg2) -> msg1 + ", " + msg2)
                .orElse("Unknown validation error")));
    }
}
