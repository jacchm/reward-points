package com.jacchm.project.adapter.repository;

import com.jacchm.project.domain.model.Metadata;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document
public class MetadataEntity {

  Integer version;
  Instant createdAt;
  Instant modifiedAt;

  public static Metadata freshMetadata() {
    return new Metadata(1, Instant.now(), Instant.now());
  }
}
