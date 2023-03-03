package com.jacchm.transaction.adapter.repository;

import com.jacchm.transaction.domain.model.Transaction;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document
public class TransactionEntity {

  @BsonId
  private String id;
  private String customerId;
  private Instant date;
  private MetadataEntity metadata;

  Transaction toDomain() {
    return Transaction.builder()
        .customerId(this.customerId)
        .id(this.id)
        .date(this.date)
        .metadata(this.metadata.toDomain())
        .build();
  }
}
