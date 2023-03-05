package com.jacchm.rewardpoints.domain;

import com.jacchm.rewardpoints.domain.model.Transaction;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.ArrayList;
import java.util.List;

public class RewardPointsCalculator {

  private static final List<Tuple2<Integer, Integer>> REWARD_POINTS_TIERS = new ArrayList<>();

  static {
    REWARD_POINTS_TIERS.add(Tuples.of(0, 0));
    REWARD_POINTS_TIERS.add(Tuples.of(50, 1));
    REWARD_POINTS_TIERS.add(Tuples.of(100, 2));
  }

  public Integer calculateRewardPointsPerTransaction(final Transaction transaction) {
    final int payment = transaction.getAmount().intValue();
    if (payment < 0) return 0;
    final int maxApplicableTier = findMaximalApplicableTier(payment);
    return calculateRewardPoints(payment, maxApplicableTier);
  }

  private int findMaximalApplicableTier(final int payment) {
    int tierNo = 0;
    for (int i = 0; i < REWARD_POINTS_TIERS.size(); i++) {
      if (payment > REWARD_POINTS_TIERS.get(i).getT1()) {
        tierNo = i;
      }
    }
    return tierNo;
  }

  private int calculateRewardPoints(int payment, final int maxApplicableTier) {
    int rewardPoints = 0;
    Tuple2<Integer, Integer> tier;
    int amountInTier;
    for (int i = maxApplicableTier; i >= 0; i--) {
      tier = REWARD_POINTS_TIERS.get(i);
      amountInTier = payment - tier.getT1();
      rewardPoints += amountInTier * tier.getT2();
      payment -= amountInTier;
    }
    return rewardPoints;
  }

}
