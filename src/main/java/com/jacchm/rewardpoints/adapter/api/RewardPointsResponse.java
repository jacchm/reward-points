package com.jacchm.rewardpoints.adapter.api;

import com.jacchm.rewardpoints.domain.model.RewardPoints;
import lombok.Builder;
import lombok.Getter;

import java.time.Month;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Builder
@Getter
class RewardPointsResponse {
  private String customerId;
  private Map<Month, Integer> rewardPointsPerMonth;
  private Integer total;

  static RewardPointsResponse of(final RewardPoints rewardPoints) {
    return RewardPointsResponse.builder()
        .customerId(rewardPoints.getCustomerId())
        .rewardPointsPerMonth(getRewardPointsSortedByMonth(rewardPoints))
        .total(calculateTotal(rewardPoints))
        .build();
  }

  private static Integer calculateTotal(final RewardPoints rewardPoints) {
    return rewardPoints.getRewardPointsPerMonth().values().stream().reduce(Integer::sum).orElse(0);
  }

  private static TreeMap<Month, Integer> getRewardPointsSortedByMonth(final RewardPoints rewardPoints) {
    return rewardPoints.getRewardPointsPerMonth()
        .entrySet()
        .stream()
        .sorted((s1, s2) -> s1.getKey().getValue() > s2.getKey().getValue() ? 1 : 0)
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            Integer::sum,
            TreeMap::new)
        );
  }

}
