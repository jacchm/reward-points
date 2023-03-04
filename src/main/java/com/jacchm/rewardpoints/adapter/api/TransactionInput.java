package com.jacchm.rewardpoints.adapter.api;

import com.jacchm.rewardpoints.domain.model.Transaction;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

record TransactionInput(
    @NotNull(message = "Transaction id must be specified.")
    String id,
    @NotNull(message = "Transaction customerId must be specified.")
    String customerId,
    @NotNull(message = "Transaction amount must be specified.")
    BigDecimal amount,
    @NotNull(message = "Transaction date must be specified.")
    Instant date) {

  Transaction toDomain() {
    return Transaction.builder()
        .id(this.id)
        .customerId(this.customerId)
        .amount(this.amount)
        .date(this.date)
        .build();
  }

}
