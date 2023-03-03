package com.jacchm.transaction.domain.model;

import lombok.Value;

import java.time.Instant;

@Value
public class Transaction {
  String id;
  String customerId;
  Instant date;
  Metadata metadata;
}
