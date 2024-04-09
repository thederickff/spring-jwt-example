package com.github.thederickff.jwtexample.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

  @GetMapping("/test")
  public String test() {
    return "Protected endpoint: works";
  }

}
