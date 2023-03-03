package com.jacchm.project.adapter.repository;

import com.jacchm.project.domain.model.Metadata;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Document
class TransactionEntity {
  private String id;
  private String customerId;
  private Instant date;
  private Metadata metadata;
}
