package com.jacchm.rewardpoints.adapter.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("reward-points")
class RewardPointsController {

  private final RewardPointsApiService rewardPointsApiService;

  @PostMapping("/calculate-db")
  Mono<ResponseEntity<RewardPointsResponse>> calculateRewardPoints(@RequestParam final String customerId) {
    return Mono.just(ResponseEntity.ok().build());
  }

  @PostMapping("/calculate")
  Mono<ResponseEntity<List<RewardPointsResponse>>> calculateRewardPoints(
      @RequestBody @Valid final List<TransactionInput> transactions) {
    log.info("Calculate reward points for transactions={}", transactions);
    return rewardPointsApiService.calculateRewardPointPerCustomerAndMonth(transactions)
        .map(ResponseEntity::ok);
  }
}
