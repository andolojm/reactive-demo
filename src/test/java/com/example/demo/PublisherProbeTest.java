package com.example.demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

// PublisherProbe
// - Create a mock publisher that can assert and query the subscribers

public class PublisherProbeTest {
  
  @Test
	public void testCommandEmptyPathIsUsed() {
		PublisherProbe<String> probe = PublisherProbe.of(Mono.just("don't panic"));

    StepVerifier.create(doAnythingButPanic(Mono.empty(), probe.mono())) 
      .expectNext("don't panic")
      .verifyComplete();

    probe.assertWasSubscribed(); 
    probe.assertWasRequested(); 
    probe.assertWasNotCancelled(); 
  }
  
  public Mono<String> doAnythingButPanic(Mono<String> commandSource, Mono<String> backupCommandSource) {
    return commandSource
      .flatMap(command -> !command.equalsIgnoreCase("PANIC") ? Mono.just(command) : Mono.empty())
      .switchIfEmpty(backupCommandSource);
  }
}