package com.jacchm.project.adapter;

import com.jacchm.project.adapter.repository.TransactionEntity;
import com.jacchm.project.domain.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = MetadataMapper.class)
public interface TransactionMapper {

  TransactionEntity toEntity(final Transaction transaction);

  Transaction toDomain(final TransactionEntity transactionEntity);

}
