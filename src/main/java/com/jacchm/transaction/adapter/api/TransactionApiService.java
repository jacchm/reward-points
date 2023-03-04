package com.jacchm.transaction.adapter.api;

import com.jacchm.transaction.domain.port.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
class TransactionApiService {

  private final TransactionService transactionService;

  Flux<TransactionResponse> fetch(final TransactionQueryParams dateParams) {
    return Mono.just(dateParams.toDomainQueryParams())
        .flatMapMany(transactionService::fetchAllByCustomerIdAndBetweenDates)
        .map(TransactionResponse::of);
  }

  Mono<TransactionResponse> create(final TransactionCreateRequest transactionCreateRequest) {
    return transactionService.createTransaction(transactionCreateRequest.toDomain())
        .map(TransactionResponse::of);
  }
}
