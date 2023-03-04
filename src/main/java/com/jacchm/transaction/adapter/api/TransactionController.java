package com.jacchm.transaction.adapter.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("transactions")
class TransactionController {

  private static final String X_TOTAL_COUNT = "X-Total-Count";

  private final TransactionApiService transactionApiService;

  @PostMapping
  Mono<ResponseEntity<TransactionResponse>> post(@Valid @RequestBody final TransactionCreateRequest transactionCreateRequest) {
    log.info("Trying to create a new transaction={}", transactionCreateRequest);
    return transactionApiService.create(transactionCreateRequest)
        .map(result -> ResponseEntity.created(URI.create(result.getId())).body(result));
  }

  @GetMapping
  Mono<ResponseEntity<List<TransactionResponse>>> get(@Valid final TransactionQueryParams queryParams) {
    log.info("Retrieving transactions for queryParams={}", queryParams);
    return transactionApiService.fetch(queryParams)
        .collectList()
        .map(ResponseEntity::ok);
  }

}
