package com.jacchm.project.domain.port;

import com.jacchm.project.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public final class TransactionService {

  public Mono<Transaction> createTransaction() {
    return Mono.empty();
  }

}
