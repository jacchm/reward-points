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

        then: "result should be correct"
        rewardPoints == expectedRewardPoints

        where:
        amount || expectedRewardPoints
        120    || 90
        50     || 0
        70     || 20
        67     || 17
    }
}
