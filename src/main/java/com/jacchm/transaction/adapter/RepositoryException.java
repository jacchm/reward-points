package com.jacchm.transaction.adapter;

import com.jacchm.transaction.domain.DomainException;
import org.springframework.http.HttpStatus;

public class RepositoryException extends DomainException {

  private static final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

  public RepositoryException(String message) {
    super(status, message);
  }
}
