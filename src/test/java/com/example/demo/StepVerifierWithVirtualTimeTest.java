package com.example.demo;

import static java.time.Duration.ofSeconds;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

// StepVerifier.withVirtualTime()
// - Given a Mono/Flux, verify integrity, order of contents
// - Operates ignoring delay logic

public class StepVerifierWithVirtualTimeTest {
  @Test
  public void testWithStepVerifierVirtualTime() {
    StepVerifier
      .withVirtualTime(() -> getVeryExcited(Mono.just("Away Day")))
      .expectSubscription()
      .thenAwait(ofSeconds(3))
      .expectNext("Away Day!!!!")
      .thenAwait(ofSeconds(2))
      .expectNext("Away Day!!!!")
      .thenAwait(ofSeconds(2))
      .expectNext("Away Day!!!!")
      .verifyComplete();
  }

  private Flux<String> getVeryExcited(Mono<String> input) {
    return Flux.interval(ofSeconds(2))
      .flatMap(tick -> input.map(str -> str + "!!!!"))
      .take(3);
  }
}