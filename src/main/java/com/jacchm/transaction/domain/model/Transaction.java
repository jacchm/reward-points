package com.jacchm.transaction.domain.model;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Builder
@Value
public class Transaction {
  String id;
  String customerId;
  Instant date;
  Metadata metadata;
}
