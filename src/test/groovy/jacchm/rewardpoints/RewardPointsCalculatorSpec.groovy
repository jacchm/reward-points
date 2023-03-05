package jacchm.rewardpoints

import com.jacchm.rewardpoints.domain.RewardPointsCalculator
import com.jacchm.rewardpoints.domain.model.Transaction
import spock.lang.Specification

import java.time.Instant

class RewardPointsCalculatorSpec extends Specification {

    private RewardPointsCalculator rewardPointsCalculator = new RewardPointsCalculator()

    def "should calculate reward points correctly"() {
        given: "transaction details"
        def transaction = Transaction.builder()
                .id("1")
                .customerId("customer-1")
                .amount(BigDecimal.valueOf(amount))
                .date(Instant.now())
                .build()

        when: "calculating reward points"
        def rewardPoints = rewardPointsCalculator.calculateRewardPointsPerTransaction(transaction)

        then: "number of calculated reward points should be equal to expected value"
        rewardPoints == expectedRewardPoints

        where:
        amount || expectedRewardPoints
        -100   || 0
        0      || 0
        1      || 0
        50     || 0
        67     || 17
        70     || 20
        100    || 50
        120    || 90 // values given in the task specification
        135    || 120
        200    || 250
        300    || 450
    }
}
