package com.jacchm.project.adapter.repository;

import com.jacchm.project.domain.model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.Instant;

public interface MongoTransactionRepository extends ReactiveMongoRepository<Transaction, String> {

  Flux<Transaction> findAllByCustomerId(final String customerId);

  Flux<Transaction> findAllByCustomerIdAndDateBetween(final String customerId, final Instant from, final Instant to);
}
