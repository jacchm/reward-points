package com.jacchm.transaction.adapter.api;

import com.jacchm.transaction.domain.model.Transaction;
import lombok.Builder;

import java.time.Instant;

@Builder
public class TransactionResponse {
  private String id;
  private String customerId;
  private Instant date;
  private MetadataResponse metadata;

  public static TransactionResponse of(Transaction transaction) {
    return TransactionResponse.builder()
        .id(transaction.getId())
        .customerId(transaction.getCustomerId())
        .date(transaction.getDate())
        .metadata(MetadataResponse.of(transaction.getMetadata()))
        .build();
  }
}
