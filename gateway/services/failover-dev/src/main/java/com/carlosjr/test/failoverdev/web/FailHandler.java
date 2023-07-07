package com.carlosjr.test.failoverdev.web;

import com.carlosjr.test.failoverdev.model.FailDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class FailHandler {
    public Mono<ServerResponse> listError(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Mono.just(FailDto.builder()
                        .failMessage("Fail message")
                        .uuid(UUID.randomUUID())
                        .build()), FailDto.class);
    }
}
