package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

import reactor.core.publisher.Hooks;

@SpringBootApplication
@EnableWebFlux
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
    Hooks.onOperatorDebug();
  }

}
