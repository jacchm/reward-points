package com.jacchm.transaction.domain.port;

import com.jacchm.transaction.adapter.api.DateRange;
import com.jacchm.transaction.domain.model.Transaction;
import reactor.core.publisher.Flux;

public interface TransactionRepository {

  Flux<Transaction> fetchAllByCustomerId(final String customerId);

  Flux<Transaction> fetchAllByCustomerIdAndDateRange(final String customerId, final DateRange dateRange);

}
