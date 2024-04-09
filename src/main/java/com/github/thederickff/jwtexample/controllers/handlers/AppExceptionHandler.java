package com.github.thederickff.jwtexample.controllers.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.thederickff.jwtexample.controllers.dto.response.ErrorResponse;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ Exception.class })
  public ResponseEntity<?> handleAll(Exception e, WebRequest request) {
    ErrorResponse response = new ErrorResponse(
      HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()
    );

    return ResponseEntity.status(response.getStatus()).body(response);
  }

}
