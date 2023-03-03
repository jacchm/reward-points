package com.jacchm.transaction.adapter;

import com.jacchm.transaction.adapter.repository.MetadataEntity;
import com.jacchm.transaction.domain.model.Metadata;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface MetadataMapper {

  MetadataEntity toEntity(final Metadata transaction);

  Metadata toDomain(final MetadataEntity transactionEntity);
}
