package com.jacchm.transaction.adapter.api;

import com.jacchm.transaction.domain.DomainException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.ServerRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
class GlobalExceptionWrapper extends DefaultErrorAttributes {

  private static final String REQUEST_ID = "requestId";
  private static final String EXCEPTION = "exception";
  private static final String PATH = "path";
  private static final String TIMESTAMP = "timestamp";
  private static final String MESSAGE = "message";
  private static final String ERROR_DETAILS = "errorDetails";

  @Override
  public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
    final var error = getError(request);

    final Map<String, Object> errorAttributes = super.getErrorAttributes(request,
        options.including(ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.EXCEPTION));
    errorAttributes.remove(REQUEST_ID);
    errorAttributes.remove(EXCEPTION);
    errorAttributes.remove(PATH);
    errorAttributes.remove(TIMESTAMP);

    if (error instanceof DomainException err) {
      final HttpStatus errorStatus = err.getStatus();
      errorAttributes.replace(ErrorAttribute.STATUS.value, errorStatus.value());
      errorAttributes.replace(ErrorAttribute.ERROR.value, errorStatus.getReasonPhrase());

      if (!CollectionUtils.isEmpty(((DomainException) error).getErrorDetails())) {
        var errorDetails = ((DomainException) error).getErrorDetails();
        errorAttributes.put(ERROR_DETAILS, errorDetails);
      }
    }
    if (error instanceof WebExchangeBindException ex) {
      var errorDetails = ex.getAllErrors()
          .stream()
          .map(DefaultMessageSourceResolvable::getDefaultMessage)
          .collect(Collectors.toList());
      errorAttributes.replace(MESSAGE, "Validation exception occurred. Please check errorDetails.");
      errorAttributes.put(ERROR_DETAILS, errorDetails);
    }

    if (error instanceof ConstraintViolationException ex) {
      var errorDetails = ex.getConstraintViolations()
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toList());
      errorAttributes.replace(MESSAGE, "Validation exception occurred. Please check errorDetails.");
      errorAttributes.put(ERROR_DETAILS, errorDetails);
      errorAttributes.put(ErrorAttribute.STATUS.value, HttpStatus.BAD_REQUEST.value());
      errorAttributes.replace(ErrorAttribute.ERROR.value, HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    return errorAttributes;
  }

  @AllArgsConstructor
  enum ErrorAttribute {
    STATUS("status"),
    ERROR("error");

    private final String value;
  }

}
