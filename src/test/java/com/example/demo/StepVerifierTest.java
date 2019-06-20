package com.example.demo;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

// StepVerifier
// - Given a Mono/Flux, verify integrity and order of contents
public class StepVerifierTest {
  @Test
  public void testWithStepVerifier() {
    StepVerifier
      .create(getExcited(Mono.just("Away Day")))
      .expectNext("Away Day!!!!")
      .verifyComplete();
  }

  private Mono<String> getExcited(Mono<String> input) {
    return input.map(str -> str + "!!!!");
  }
}