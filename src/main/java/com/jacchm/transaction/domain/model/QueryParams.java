package com.jacchm.transaction.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QueryParams {

  private String customerId;
  private Instant fromDate;
  private Instant toDate;
}
