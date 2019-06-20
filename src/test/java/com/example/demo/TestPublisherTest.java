package com.example.demo;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

// TestPublisher
// - Create a mock publisher for testing subscribers
public class TestPublisherTest {

  @Test
  public void testTestPublisher() {
    TestPublisher<String> testPublisher = TestPublisher.create();

    StepVerifier
      .create(new HypeConverter(testPublisher.flux()).getHype())
      .then(() -> testPublisher.emit("away day", "aWaY dAy", "away day :("))
      .expectNext("AWAY DAY", "AWAY DAY", "AWAY DAY :)")
      .verifyComplete();
  }

  class HypeConverter {
    private final Flux<String> source;
 
    HypeConverter(Flux<String> source) {
      this.source = source;
    }

    private String turnFrownUpsideDown(String input) {
      return input.replace('(', ')');
    }
 
    Flux<String> getHype() {
      return source
        .map(String::toUpperCase)
        .map(this::turnFrownUpsideDown);
    }   
  }
}