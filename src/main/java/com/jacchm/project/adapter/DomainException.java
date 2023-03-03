package com.jacchm.project.adapter;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
abstract class DomainException extends RuntimeException {

  private final HttpStatus status;
  private List<String> errorDetails;

  public DomainException(final HttpStatus status, final String message) {
    super(message);
    this.status = status;
  }

  public DomainException(final HttpStatus status, final String message, List<String> errorDetails) {
    this(status, message);
    this.errorDetails = errorDetails;
  }
}
