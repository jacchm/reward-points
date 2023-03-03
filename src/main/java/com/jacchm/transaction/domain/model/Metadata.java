package com.jacchm.transaction.domain.model;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class Metadata {

  Integer version;
  Instant createdAt;
  Instant modifiedAt;

}
