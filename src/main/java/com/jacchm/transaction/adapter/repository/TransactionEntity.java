package com.jacchm.transaction.adapter.repository;

import com.jacchm.transaction.domain.model.Transaction;
import lombok.Builder;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@Document
public class TransactionEntity {

  @BsonId
  private String id;
  private String customerId;
  private BigDecimal amount;
  private Instant date;
  private MetadataEntity metadata;

  Transaction toDomain() {
    return Transaction.builder()
        .customerId(this.customerId)
        .id(this.id)
        .amount(this.amount)
        .date(this.date)
        .metadata(this.metadata.toDomain())
        .build();
  }

  static TransactionEntity of(Transaction domain) {
    return TransactionEntity
        .builder()
        .id(domain.getId())
        .customerId(domain.getCustomerId())
        .amount(domain.getAmount())
        .date(domain.getDate())
        .metadata(MetadataEntity.freshMetadata())
        .build();
  }
}
