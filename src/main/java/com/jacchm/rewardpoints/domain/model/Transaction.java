package com.jacchm.rewardpoints.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class Transaction {
  private String id;
  private String customerId;
  private BigDecimal amount;
  private Instant date;

}
