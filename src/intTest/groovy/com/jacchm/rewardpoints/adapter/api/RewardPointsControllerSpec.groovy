package com.jacchm.rewardpoints.adapter.api


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

import java.util.stream.Collectors

@ActiveProfiles(value = "test")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = [
                "spring.datasource.url=r2dbc:tc:mongodb:///transactionDB?TC_IMAGE_TAG=latest"
        ]
)
@Testcontainers
class RewardPointsControllerSpec extends Specification implements RewardPointsControllerTestData {

    @Shared
    private MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest")

    @Autowired
    private TestRestTemplate restTemplate

    def "should calculate the number of reward points correctly and return status 200"() {
        given: 'list of transactions from the last months'
        def transactions = prepareListOfTransactions()

        when: 'trying to create a new user'
        def responseEntity = restTemplate.exchange(
                "/reward-points/calculate",
                HttpMethod.POST,
                new HttpEntity(transactions),
                new ParameterizedTypeReference<List<RewardPointsResponse>>() {},
                Map.of()
        )

        then: 'response should return status 201 CREATED'
        responseEntity.statusCode == HttpStatus.OK

        and: 'number and ids of customers present in given transactions should be equal in response'
        def responseCustomerList = responseEntity
                .getBody()
                .stream()
                .map(rewardPointsResp -> rewardPointsResp.getCustomerId())
                .collect(Collectors.toList())
        responseCustomerList.containsAll(CUSTOMERS)
        responseCustomerList.size() == CUSTOMERS.size()

        and: 'number of total reward points calculated for every customer should be equal to expected value'
        def totalRewardPointsPerCustomer = responseEntity.getBody().stream()
                .collect(Collectors.toMap(RewardPointsResponse::getCustomerId, RewardPointsResponse::getTotal))
        totalRewardPointsPerCustomer == TOTAL_REWARD_POINTS_PER_CUSTOMER_ID
    }
}
