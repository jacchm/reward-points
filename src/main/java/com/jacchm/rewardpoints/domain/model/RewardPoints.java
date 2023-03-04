package com.jacchm.rewardpoints.domain.model;

import com.jacchm.transaction.adapter.api.MonthEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class RewardPoints {
  private String customerId;
  private Map<MonthEnum, Integer> rewardPoints;
}
