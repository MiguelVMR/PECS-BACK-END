package com.pecs.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class PecsException extends RuntimeException {
  private final String message;

  private final HttpStatus httpStatus;

  public PecsException(String message, HttpStatus httpStatus) {
      this.message = message;
      this.httpStatus = httpStatus;
  }
}
