package com.jacchm.rewardpoints.config;

import com.jacchm.rewardpoints.domain.RewardPointsCalculator;
import com.jacchm.rewardpoints.domain.port.RewardPointsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RewardPointsConfig {

  @Bean
  public RewardPointsCalculator rewardPointsCalculator() {
    return new RewardPointsCalculator();
  }

  @Bean
  public RewardPointsService rewardPointsService() {
    return new RewardPointsService(rewardPointsCalculator());
  }

}
