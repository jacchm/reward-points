package com.jacchm.transaction.adapter.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jacchm.transaction.domain.model.Transaction;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@Builder
record TransactionCreateRequest(
    @NotNull(message = "customerId must be specified.")
    String customerId,
    @NotNull(message = "amount must be specified.")
    @JsonDeserialize(using = BigDecimalCustomDeserializer.class)
    BigDecimal amount,
    @NotNull(message = "date must be specified.")
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
