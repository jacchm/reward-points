package com.jacchm.transaction.adapter;

import com.jacchm.transaction.adapter.repository.TransactionEntity;
import com.jacchm.transaction.domain.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = MetadataMapper.class)
public interface TransactionMapper {

  TransactionEntity toEntity(final Transaction transaction);

  Transaction toDomain(final TransactionEntity transactionEntity);

}
