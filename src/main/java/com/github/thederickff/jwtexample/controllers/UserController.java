package com.github.thederickff.jwtexample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.thederickff.jwtexample.controllers.dto.request.UserSignInRequest;
import com.github.thederickff.jwtexample.controllers.dto.request.UserSignUpRequest;
import com.github.thederickff.jwtexample.services.AuthService;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class UserController {

  private final AuthService authService;

  @Autowired
  public UserController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/signUp")
  public ResponseEntity<?> signUp(@RequestBody UserSignUpRequest request) {
    return ResponseEntity.ok(this.authService.signUp(request));
  }

  @PostMapping("/signIn")
  public ResponseEntity<?> signIn(@RequestBody UserSignInRequest request) {
    return ResponseEntity.ok(this.authService.signIn(request));
  }

  @GetMapping("/test")
  public String test() {
    return "Public endpoint: works";
  }

}
