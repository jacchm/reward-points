package com.jacchm.project.adapter.api;

import com.jacchm.project.domain.port.TransactionService;
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
