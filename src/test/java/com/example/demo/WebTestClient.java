package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

// WebTestClient
// - Creates a mock WebClient
// - Assert on service responses
// - Useful for API testing
//    * Note, for this test to actually pass, we'd need to mock the service calls

class WebTestClientTest {
  @Test
  public void testAllTheThings() {
    Service service = new Service();
    Handler handler = new Handler(service);
    RouterFunction<ServerResponse> router = new Router().routes(handler);

    WebTestClient client = WebTestClient.bindToRouterFunction(router).build();
    client
      .post()
      .uri("/submit")
      .body(BodyInserters.fromObject("Away Day?"))
      .exchange()
      .expectStatus().isOk()
      .expectBody().json("{}");
  }
}