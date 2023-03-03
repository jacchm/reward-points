package com.jacchm.project.domain.model;

import lombok.Value;

import java.time.Instant;

@Value
public class Metadata {

  Integer version;
  Instant createdAt;
  Instant modifiedAt;

}
