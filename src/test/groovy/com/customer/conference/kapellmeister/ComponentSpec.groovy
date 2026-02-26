package com.customer.conference.kapellmeister

import io.restassured.RestAssured
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

@Testcontainers
@ActiveProfiles("component-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = [ConferenceKapellmeisterCoreApplication])
abstract class ComponentSpec extends Specification {

    static final String USERNAME = "user"
    static final String PASSWORD = "kapellmeister"
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:18.2")
            .withDatabaseName("conference_kapellmeister")
            .withUsername("test")
            .withPassword("test")

    @LocalServerPort
    Integer port

    static {
        postgres.start()
    }

    def setup() {
        RestAssured.port = port
    }

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres.&getJdbcUrl)
        registry.add("spring.datasource.username", postgres.&getUsername)
        registry.add("spring.datasource.password", postgres.&getPassword)
    }

    static def restAssuredGivenJson() {
        return RestAssured.given()
                .contentType('application/json')
                .auth().basic(USERNAME, PASSWORD)
    }
}
