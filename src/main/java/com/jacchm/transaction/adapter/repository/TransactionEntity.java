package com.jacchm.transaction.adapter.repository;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document
public class TransactionEntity {
  private String id;
  private String customerId;
  private Instant date;
  private MetadataEntity metadata;
}
