package com.jacchm.transaction.adapter.api;

import com.jacchm.transaction.domain.model.Metadata;
import lombok.Builder;

import java.time.Instant;

@Builder
public class MetadataResponse {

  Integer version;
  Instant createdAt;
  Instant modifiedAt;

  static MetadataResponse of(final Metadata metadata) {
    return MetadataResponse.builder()
        .version(metadata.getVersion())
        .createdAt(metadata.getCreatedAt())
        .modifiedAt(metadata.getModifiedAt())
        .build();
  }

}
