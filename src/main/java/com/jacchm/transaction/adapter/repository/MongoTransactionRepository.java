package com.jacchm.transaction.adapter.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.Instant;

public interface MongoTransactionRepository extends ReactiveMongoRepository<TransactionEntity, String> {

  Flux<TransactionEntity> findAllByCustomerId(final String customerId);

  Flux<TransactionEntity> findAllByCustomerIdAndDateBetween(final String customerId, final Instant from, final Instant to);
}
