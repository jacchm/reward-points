package com.jacchm.rewardpoints.domain.port;

import com.jacchm.rewardpoints.domain.model.RewardPoints;
import com.jacchm.rewardpoints.domain.model.Transaction;
import com.jacchm.transaction.adapter.api.MonthEnum;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public final class RewardPointsService {

  private static final Map<Integer, Integer> REWARD_POINTS_TIERS = Map.of(
      50, 0,
      100, 2,
      Integer.MAX_VALUE, 1);

  public Flux<RewardPoints> calculateRewardPoints(final List<Transaction> transactions) {
    return Flux.fromIterable(transactions)
        .groupBy(Transaction::getCustomerId)
        .flatMap(groupedFlux -> groupedFlux
            .map(Transaction::getAmount)
            .map(this::calculateNumberOfRewardPoints)
            .map(rewardPointsNumber ->
                RewardPoints.of(groupedFlux.key(), Map.of(MonthEnum.JANUARY, rewardPointsNumber)))
        );
  }

  private Integer calculateNumberOfRewardPoints(final BigDecimal payment) {
    int numberOfRewardPoints = 0;
    int paymentRounded = payment.intValue();
    if (paymentRounded <= 50) {
      return 0;
    }
    if (paymentRounded > 100) {
      numberOfRewardPoints += paymentRounded - 100;
      paymentRounded -= paymentRounded - 100;
    }
    if (paymentRounded <= 100) {
      numberOfRewardPoints += (paymentRounded - 50) * 2;
    }
    return numberOfRewardPoints;
  }

}
