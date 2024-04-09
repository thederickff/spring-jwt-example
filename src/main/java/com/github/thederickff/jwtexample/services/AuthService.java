package com.github.thederickff.jwtexample.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.thederickff.jwtexample.controllers.dto.request.UserSignInRequest;
import com.github.thederickff.jwtexample.controllers.dto.request.UserSignUpRequest;
import com.github.thederickff.jwtexample.controllers.dto.response.UserSignInResponse;
import com.github.thederickff.jwtexample.controllers.dto.response.UserSignUpResponse;
import com.github.thederickff.jwtexample.models.User;
import com.github.thederickff.jwtexample.repositories.UserRepository;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthService(
    UserRepository userRepository,
    PasswordEncoder passwordEncoder,
    JwtService jwtService,
    AuthenticationManager authenticationManager
  ) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  public UserSignUpResponse signUp(UserSignUpRequest request) {
    User user = new User();
    user.setDisplayName(request.getDisplayName());
    user.setEmail(request.getEmail());
    user.setPassword(this.passwordEncoder.encode(request.getPassword()));
    user = this.userRepository.save(user);

    return mapperToUserSignUpResponse(user);
  }

  public UserSignInResponse signIn(UserSignInRequest request) {
    this.authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );

    User user = this.userRepository.findByEmail(request.getEmail()).orElse(null);
    String token = this.jwtService.generateToken(user);

    return mapperToUserSignInResponse(token, this.jwtService.getExpirationTime());
  }

  private UserSignUpResponse mapperToUserSignUpResponse(User user) {
    UserSignUpResponse response = new UserSignUpResponse();
    response.setId(user.getId());
    response.setDisplayName(user.getDisplayName());
    response.setEmail(user.getEmail());

    return response;
  }

  private UserSignInResponse mapperToUserSignInResponse(String token, long expiresIn) {
    UserSignInResponse response = new UserSignInResponse();
    response.setToken(token);
    response.setExpiresIn(expiresIn);

    return response;
  }
}
