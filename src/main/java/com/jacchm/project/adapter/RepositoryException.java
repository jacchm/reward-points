package com.jacchm.project.adapter;

import org.springframework.http.HttpStatus;

public class RepositoryException extends DomainException {

  private static final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

  public RepositoryException(String message) {
    super(status, message);
  }
}
