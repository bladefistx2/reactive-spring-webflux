package com.reactivespring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebFluxTest(controllers = FluxMonoController.class)
@AutoConfigureWebTestClient
public class FluxAndMonoControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @Test
    void streamTest() {
        var stream = webTestClient
                .get()
                .uri("/stream")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(Long.class)
                .getResponseBody();

        StepVerifier.create(stream)
                .expectNext(0L, 1L, 2L)
                .thenCancel()
                .verify();
    }

    @Test
    void fluxApproach1() {

        var flux = webTestClient
                .get()
                .uri("/flux")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(String.class)
                .getResponseBody();

        StepVerifier.create(flux)
                .expectNext("123")
                .verifyComplete();
    }

    @Test
    void fluxApproach2() {

        webTestClient.
                get()
                .uri("/flux")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class)
                .consumeWith(listEntityExchangeResult -> {

                    var responseBody = listEntityExchangeResult.getResponseBody();
                    assertEquals("123", responseBody);

                });


    }
}
