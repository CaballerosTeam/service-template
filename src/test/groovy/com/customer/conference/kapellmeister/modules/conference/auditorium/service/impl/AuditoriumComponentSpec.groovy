package com.customer.conference.kapellmeister.modules.conference.auditorium.service.impl

import com.customer.conference.kapellmeister.ComponentSpec
import com.customer.conference.kapellmeister.api.model.AuditoriumView
import com.customer.conference.kapellmeister.modules.conference.auditorium.repository.AuditoriumRepository
import org.springframework.beans.factory.annotation.Autowired
import org.apache.http.HttpStatus

class AuditoriumComponentSpec extends ComponentSpec {

    @Autowired
    AuditoriumRepository roomRepository

    def "save should create a new entity when entity with given ID does not exits"() {
        given:
        AuditoriumView auditoriumView = AuditoriumView.builder()
            .name("Testing Auditorium 1")
            .build()

        expect:
        restAssuredGivenJson()
            .body(auditoriumView)
            .when()
            .log().all()
            .post("/api/v1/auditoriums")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_NO_CONTENT)
    }
}
