package com.jacchm.transaction.adapter.repository;

import com.jacchm.transaction.adapter.RepositoryException;
import com.jacchm.transaction.domain.model.QueryParams;
import com.jacchm.transaction.domain.model.Transaction;
import com.jacchm.transaction.domain.port.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
@Component
public class DBTransactionRepository implements TransactionRepository {

  private static final String SERVER_ERR_MSG = """
      The server has encountered an error during processing the request data while trying to connect to the database.""";

  private final MongoTransactionRepository mongoRepository;

  @Override
  public Mono<Transaction> create(final Transaction transaction) {
    final TransactionEntity newTransaction = TransactionEntity.of(transaction);
    return mongoRepository.save(newTransaction)
        .doOnError(err -> log.error("Could not create a new transaction with body={}. Exception={}", newTransaction, err))
        .onErrorMap(err -> new RepositoryException(SERVER_ERR_MSG))
        .map(TransactionEntity::toDomain);
  }

  @Override
  public Flux<Transaction> fetchAllByQueryParams(final QueryParams queryParams) {
    final String customerId = queryParams.getCustomerId();
    final Instant fromDate = queryParams.getFromDate();
    final Instant toDate = queryParams.getToDate();

    final Flux<TransactionEntity> findByCustomerIdAndDateRangeFlux = fetchAllByCustomerIdAndBetweenDates(customerId, queryParams);
    final Flux<TransactionEntity> findByCustomerIdFlux = fetchAllByCustomerId(customerId);

//    return (Objects.isNull(queryParams) ? findByCustomerIdFlux : findByCustomerIdAndDateRangeFlux)
    return findByCustomerIdFlux
        .onErrorMap(err -> new RepositoryException(SERVER_ERR_MSG))
        .map(TransactionEntity::toDomain);
  }

  private Flux<TransactionEntity> fetchAllByCustomerIdAndBetweenDates(final String customerId, final QueryParams queryParams) {
    return mongoRepository.findAllByCustomerIdAndDateBetween(customerId, queryParams.getFromDate(), queryParams.getToDate())
        .doOnError(err -> log.error("Could not fetch all the transactions by customerId={} from={} to={}",
            customerId, queryParams.getFromDate(), queryParams.getToDate()));
  }

  private Flux<TransactionEntity> fetchAllByCustomerId(final String customerId) {
    return mongoRepository.findAllByCustomerId(customerId)
        .doOnError(err -> log.error("Could not fetch all the transactions by customerId={}", customerId));
  }

}
