package com.jacchm.rewardpoints.domain.port;

import com.jacchm.rewardpoints.domain.RewardPointsCalculator;
import com.jacchm.rewardpoints.domain.model.RewardPoints;
import com.jacchm.rewardpoints.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public final class RewardPointsService {

  private final RewardPointsCalculator calculator;

  public Flux<RewardPoints> calculateRewardPoints(final List<Transaction> transactions) {
    return Flux.fromIterable(transactions)
        .groupBy(Transaction::getCustomerId)
        .flatMap(customerIdTransactionFlux -> customerIdTransactionFlux
            .groupBy(x -> x.getDate().atZone(ZoneId.systemDefault()).getMonth())
            .flatMap(dateTransactionFlux -> dateTransactionFlux
                .map(calculator::calculateRewardPointsPerTransaction)
                .reduce(Integer::sum)
                .map(rewardPoints -> Map.of(dateTransactionFlux.key(), rewardPoints))
            )
            .doOnNext(next -> log.info("next={}", next))
            .reduce((m1, m2) -> Stream.concat(m1.entrySet().stream(), m2.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
            .map(rewardPointsMap -> RewardPoints.of(customerIdTransactionFlux.key(), rewardPointsMap))
        )
        .doOnNext(next -> log.info("rewardPoints={}", next));
  }

}
