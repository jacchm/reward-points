package com.jacchm.rewardpoints.adapter.api;

import com.jacchm.rewardpoints.domain.port.RewardPointsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Component
class RewardPointsApiService {

  private final RewardPointsService rewardPointsService;

  Mono<List<RewardPointsResponse>> calculateRewardPointPerCustomerAndMonth(final List<TransactionInput> transactions) {
    return Flux.fromIterable(transactions)
        .map(TransactionInput::toDomain)
        .collectList()
        .flatMapMany(rewardPointsService::calculateRewardPoints)
        .map(RewardPointsResponse::of)
        .collectList();
  }
}
