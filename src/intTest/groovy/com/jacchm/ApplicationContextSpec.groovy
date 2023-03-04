package com.jacchm

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@ActiveProfiles(value = "test")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = [
                "spring.datasource.url=r2dbc:tc:mongodb:///training?TC_IMAGE_TAG=latest"
        ]
)
@Testcontainers
class ApplicationContextSpec extends Specification {

    @Shared
    MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest")

    @Autowired
    Environment env

    def "contextLoads"() {
        expect:
        1 == 1
    }
}
