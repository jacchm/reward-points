package com.jacchm.project.domain.port;

import com.jacchm.project.adapter.api.DateRange;
import com.jacchm.project.domain.model.Transaction;
import reactor.core.publisher.Flux;

public interface TransactionRepository {

  Flux<Transaction> fetchAllByCustomerId(final String customerId);

  Flux<Transaction> fetchAllByCustomerIdAndDateRange(final String customerId, final DateRange dateRange);

}
