package com.jacchm.transaction.domain.port;

import com.jacchm.transaction.domain.model.QueryParams;
import com.jacchm.transaction.domain.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionRepository {

  Mono<Transaction> create(final Transaction transaction);

  Flux<Transaction> fetchAllByQueryParams(final QueryParams queryParams);

}
