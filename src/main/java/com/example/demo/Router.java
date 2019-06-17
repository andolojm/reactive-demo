package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

// - Creates an HTTP endpoint
// - Publishes to a Handler bean
// - Can export multiple routes

@Component
public class Router {

  @Bean
  RouterFunction<ServerResponse> routes(Handler handler) {
    return route(POST("/submit"), handler::process)
      .andRoute(POST("/submit/internal"), handler::processInternalPost);
  }
}