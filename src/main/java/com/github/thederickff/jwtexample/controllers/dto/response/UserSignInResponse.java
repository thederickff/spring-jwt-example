package com.github.thederickff.jwtexample.controllers.dto.response;

public class UserSignInResponse {

  private String token;
  private Long expiresIn;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Long getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(Long expiresIn) {
    this.expiresIn = expiresIn;
  }

}
