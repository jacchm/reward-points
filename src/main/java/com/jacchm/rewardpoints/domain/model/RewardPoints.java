package com.jacchm.rewardpoints.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.util.Map;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class RewardPoints {
  private String customerId;
  private Map<Month, Integer> rewardPointsPerMonth;
}
