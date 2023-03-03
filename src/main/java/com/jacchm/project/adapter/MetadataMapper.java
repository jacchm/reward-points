package com.jacchm.project.adapter;

import com.jacchm.project.adapter.repository.MetadataEntity;
import com.jacchm.project.domain.model.Metadata;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface MetadataMapper {

  MetadataEntity toEntity(final Metadata transaction);

  Metadata toDomain(final MetadataEntity transactionEntity);
}
