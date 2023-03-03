package com.jacchm.transaction.adapter.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("transactions")
class TransactionController {

  private static final String X_TOTAL_COUNT = "X-Total-Count";

  private final TransactionApiService transactionApiService;

  @GetMapping
  Mono<ResponseEntity<List<TransactionResponse>>> get(@RequestParam final String customerId,
                                                      final DateRange month) {
    log.debug("Retrieving transactions for customerId={} and month={}", customerId, month);
    return transactionApiService.fetch(customerId)
        .collectList()
        .map(ResponseEntity::ok);
  }

}
