package com.jacchm.transaction.config;

import com.jacchm.transaction.domain.port.TransactionRepository;
import com.jacchm.transaction.domain.port.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TransactionConfig {

  @Bean
  public TransactionService transactionService(final TransactionRepository transactionRepository) {
    return new TransactionService(transactionRepository);
  }


}
