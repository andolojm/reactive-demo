package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

// The Service
// - Handle outbound I/O
// - Reactive implementation, standard concept

@Component
public class Service {

  private WebClient client = WebClient.builder()
    .baseUrl("http://localhost:3000")
    .build();

  public Mono<String> call(String input) {
    return client.post()
      .uri("/post")
      .accept(MediaType.APPLICATION_JSON)
      .body(BodyInserters.fromObject(input))
      .retrieve()
      .bodyToMono(String.class);
  }

  public Mono<String> poll(String input) {
    return client.get()
      .uri("/get")
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToMono(String.class);
  }
}