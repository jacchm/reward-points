package com.jacchm.transaction.adapter.api;

import com.jacchm.transaction.domain.model.Transaction;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
record TransactionCreateRequest(
    String customerId,
    BigDecimal amount,
    Instant date
) {

  Transaction toDomain() {
    return Transaction.builder()
        .customerId(this.customerId)
        .amount(this.amount)
        .date(this.date)
        .build();
  }

}
