package com.jacchm.rewardpoints.adapter.api;

import com.jacchm.rewardpoints.domain.model.RewardPoints;
import com.jacchm.transaction.adapter.api.MonthEnum;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
class RewardPointsResponse {
  private String customerId;
  private Map<MonthEnum, Integer> rewardPoints;

  static RewardPointsResponse of(final RewardPoints rewardPoints) {
    return RewardPointsResponse.builder()
        .customerId(rewardPoints.getCustomerId())
        .rewardPoints(rewardPoints.getRewardPoints())
        .build();
  }

}
