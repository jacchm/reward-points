package com.jacchm.project.adapter.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("transactions")
class TransactionController {

  private static final String X_TOTAL_COUNT = "X-Total-Count";

  @GetMapping("{id}")
  Mono<ResponseEntity<Void>> get(@RequestParam final String username,
                                 @RequestParam(required = false, defaultValue = "false") final String month) {
    log.debug("Retrieving transactions for username={} and month={}", username, month);
    return Mono.just(ResponseEntity.ok().build());
  }

}
