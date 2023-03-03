package com.jacchm.transaction.adapter.api;

import com.jacchm.transaction.adapter.TransactionMapper;
import com.jacchm.transaction.domain.port.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
class TransactionApiService {

  private final TransactionService transactionService;
  private final TransactionMapper transactionMapper;

  Mono<TransactionResponse> get(final String id) {
    return Mono.empty();
  }

}
