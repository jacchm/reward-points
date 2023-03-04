package com.jacchm.rewardpoints.domain;

import com.jacchm.rewardpoints.domain.model.Transaction;

import java.util.Map;

public class RewardPointsCalculator {

  private static final Map<Integer, Integer> REWARD_POINTS_TIERS = Map.of(
      50, 0,
      100, 2,
      Integer.MAX_VALUE, 1);

  public Integer calculateRewardPointsPerTransaction(final Transaction transaction) {
    int numberOfRewardPoints = 0;
    int paymentRounded = transaction.getAmount().intValue();
    if (paymentRounded <= 50) {
      return 0;
    }
    if (paymentRounded > 100) {
      numberOfRewardPoints += (paymentRounded - 100) * 2;
      paymentRounded -= paymentRounded - 100;
    }
    if (paymentRounded <= 100) {
      numberOfRewardPoints += (paymentRounded - 50);
    }
    return numberOfRewardPoints;
  }

}
