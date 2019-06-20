package com.example.demo;

import static java.time.Duration.ofMillis;
import static reactor.core.publisher.Flux.range;
import static reactor.core.publisher.Mono.delay;

import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.Exceptions;
import reactor.core.publisher.Mono;

// The handler
// - Subscribes to a Router
// - Main logic orchestrator
// - 1 Handler : 1 Route

@Component
public class Handler {
  private final Service service;
  private int retryCount = 10;
  private final int retryIntervalMs = 1000;

  Handler(Service service) {
    this.service = service;
  }

  public Mono<ServerResponse> process(ServerRequest request) {
    return request
      .bodyToMono(String.class)
      .doOnEach(requestBody -> System.out.println(requestBody))
      .flatMap(service::call)
      .flatMap(service::poll)
      .retryBackoff(retryCount, ofMillis(retryIntervalMs), ofMillis(retryIntervalMs))
      .flatMap(this::jsonResponse);
  }

  private Mono<ServerResponse> jsonResponse(String body) {
    return ServerResponse
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(Mono.just(body), String.class);
  }
}