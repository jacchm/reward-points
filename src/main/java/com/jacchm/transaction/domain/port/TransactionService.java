package com.jacchm.transaction.domain.port;

import com.jacchm.transaction.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public final class TransactionService {

  private final TransactionRepository transactionRepository;

  public Mono<Transaction> createTransaction() {
    return Mono.empty();
  }

  public Flux<Transaction> fetchAllByCustomerId(final String customerId) {
    return transactionRepository.fetchAllByCustomerId(customerId);
  }

}
