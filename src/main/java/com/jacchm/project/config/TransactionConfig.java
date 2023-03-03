package com.jacchm.project.config;

import com.jacchm.project.domain.port.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TransactionConfig {
  @Bean
  public TransactionService transactionService() {
    return new TransactionService();
  }


}
