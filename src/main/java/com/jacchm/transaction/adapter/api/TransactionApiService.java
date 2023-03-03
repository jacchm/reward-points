package com.jacchm.transaction.adapter.api;

import com.jacchm.transaction.domain.port.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
class TransactionApiService {

  private final TransactionService transactionService;

  Flux<TransactionResponse> fetch(final String customerId) {
    return transactionService.fetchAllByCustomerId(customerId)
        .map(TransactionResponse::of);
  }

}
