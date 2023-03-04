package com.jacchm.transaction.adapter.repository;

import com.jacchm.transaction.domain.DomainException;
import org.springframework.http.HttpStatus;

class RepositoryException extends DomainException {

  private static final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

  public RepositoryException(String message) {
    super(status, message);
  }
}
