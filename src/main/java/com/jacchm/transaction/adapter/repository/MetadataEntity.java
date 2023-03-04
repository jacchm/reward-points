package com.jacchm.transaction.adapter.repository;

import com.jacchm.transaction.domain.model.Metadata;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@AllArgsConstructor
@Data
@Builder
@Document
public class MetadataEntity {

  Integer version;
  Instant createdAt;
  Instant modifiedAt;

  public static MetadataEntity freshMetadata() {
    return new MetadataEntity(1, Instant.now(), Instant.now());
  }

  Metadata toDomain() {
    return Metadata.builder()
        .version(this.version)
        .createdAt(this.createdAt)
        .modifiedAt(this.modifiedAt)
        .build();
  }

  static MetadataEntity of(final Metadata metadata) {
    return MetadataEntity
        .builder()
        .version(metadata.getVersion())
        .createdAt(metadata.getCreatedAt())
        .modifiedAt(metadata.getModifiedAt())
        .build();
  }
}
