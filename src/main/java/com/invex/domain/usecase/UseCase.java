package com.invex.domain.usecase;

import com.invex.domain.usecase.request.Request;
import com.invex.domain.usecase.response.Response;
import reactor.core.publisher.Mono;

public abstract class UseCase {
    protected abstract Mono<Response> doExecute(Request request);

    public Mono<Response> execute(Request request) {
        // Default implementation can be overridden by subclasses
        return doExecute(request);
    }
}
