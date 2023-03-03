package com.jacchm.transaction.adapter.repository;

import com.jacchm.transaction.adapter.RepositoryException;
import com.jacchm.transaction.adapter.api.DateRange;
import com.jacchm.transaction.domain.model.Transaction;
import com.jacchm.transaction.domain.port.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@RequiredArgsConstructor
@Component
public class DBTransactionRepository implements TransactionRepository {

  private static final String SERVER_ERR_MSG = """
      The server has encountered an error during processing the request data while trying to connect to the database.""";

  private final MongoTransactionRepository mongoRepository;

  @Override
  public Flux<Transaction> fetchAllByCustomerId(final String customerId) {
    return mongoRepository.findAllByCustomerId(customerId)
        .doOnError(err -> log.error("Could not fetch all the transactions by customerId={}", customerId))
        .onErrorMap(err -> new RepositoryException(SERVER_ERR_MSG))
        .map(TransactionEntity::toDomain);
  }

  @Override
  public Flux<Transaction> fetchAllByCustomerIdAndDateRange(final String customerId, final DateRange dateRange) {
    return mongoRepository.findAllByCustomerIdAndDateBetween(customerId, dateRange.getFrom(), dateRange.getTo())
        .doOnError(err -> log.error("Could not fetch all the transactions by customerId={} from={} to={}",
            customerId, dateRange.getFrom(), dateRange.getTo()))
        .onErrorMap(err -> new RepositoryException(SERVER_ERR_MSG))
        .map(TransactionEntity::toDomain);
  }

}
