package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

// The handler
// - Subscribes to a Router
// - Main logic orchestrator
// - 1 Handler : 1 Route

@Component
public class Handler {
  private final Service service;

  Handler(Service service) {
    this.service = service;
  }

  public Mono<ServerResponse> process(ServerRequest request) {
    return defaultResponseWrapper(request
      .bodyToMono(String.class)
      .flatMap(service::call));
  }

  private Mono<ServerResponse> defaultResponseWrapper(Mono<String> body) {
    return ServerResponse
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(body, String.class);
  }

  public Mono<ServerResponse> processInternalPost(ServerRequest request) {
    return defaultResponseWrapper(Mono.just("{}"));
  }
}