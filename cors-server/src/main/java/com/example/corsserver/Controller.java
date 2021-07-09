package com.example.corsserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @GetMapping("/hello")
  public Hello sayHello() {
    System.out.println("***--***");
    return new Hello("world");
  }
}
