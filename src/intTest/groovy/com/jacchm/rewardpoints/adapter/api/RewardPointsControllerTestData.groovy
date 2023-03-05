package com.jacchm.rewardpoints.adapter.api

import com.jacchm.rewardpoints.domain.model.Transaction

trait RewardPointsControllerTestData {

    static final String CUSTOMER_1 = "customer-1"
    static final String CUSTOMER_2 = "customer-2"
    static final String CUSTOMER_3 = "customer-3"
    static final List<String> CUSTOMERS = List.of(CUSTOMER_1, CUSTOMER_2, CUSTOMER_3)
    static final Map<String, Integer> TOTAL_REWARD_POINTS_PER_CUSTOMER_ID = Map.of(
            CUSTOMER_1, 40, CUSTOMER_2, 200, CUSTOMER_3, 850)

    static List<Transaction> prepareListOfTransactions() {
        [
                [
                        "id"        : UUID.randomUUID(),
                        "customerId": CUSTOMER_1,
                        "date"      : "2023-12-01T00:00:00Z",
                        "amount"    : 60
                ],
                [
                        "id"        : UUID.randomUUID(),
                        "customerId": CUSTOMER_1,
                        "date"      : "2023-02-01T00:00:00Z",
                        "amount"    : 80
                ],
                [
                        "id"        : UUID.randomUUID(),
                        "customerId": CUSTOMER_2,
                        "date"      : "2023-02-10T00:00:00Z",
                        "amount"    : 100
                ],
                [
                        "id"        : UUID.randomUUID(),
                        "customerId": CUSTOMER_2,
                        "date"      : "2023-12-04T00:00:00Z",
                        "amount"    : 135
                ],
                [
                        "id"        : UUID.randomUUID(),
                        "customerId": CUSTOMER_2,
                        "date"      : "2023-12-01T00:00:00Z",
                        "amount"    : 80
                ],
                [
                        "id"        : UUID.randomUUID(),
                        "customerId": CUSTOMER_3,
                        "date"      : "2022-01-04T00:00:00Z",
                        "amount"    : 500
                ]
        ] as List<Transaction>
    }

}
