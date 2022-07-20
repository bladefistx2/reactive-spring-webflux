package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    public void namesFlux(){
        // given

        // when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();
        // then
        StepVerifier.create(namesFlux)
//                .expectNext("James", "Mary", "Moses")
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    public void namesFluxMap() {
        var namesFluxMap = fluxAndMonoGeneratorService.namesFluxMap(5);

        StepVerifier.create(namesFluxMap)
                .expectNext("5-JAMES", "5-MOSES")
                .verifyComplete();
    }

    @Test
    public void nameMono() {
        var monoFlux = fluxAndMonoGeneratorService.nameMono();

        StepVerifier.create(monoFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void namesFluxFlatMap() {
        var namesFluxFlatMap = fluxAndMonoGeneratorService.namesFluxFlatMap();

        StepVerifier.create(namesFluxFlatMap)
                .expectNext("J","A","M","E","S","M","A","R","Y","M","O","S","E","S")
                .verifyComplete();
    }


    @Test
    public void nameMonoFlatMap() {
        var nameMonoFlat = fluxAndMonoGeneratorService.nameMonoFlatMap();

        StepVerifier.create(nameMonoFlat)
                .expectNext(List.of("J","E","S","U","S"))
                .verifyComplete();
    }

    @Test
    public void nameMonoFlatMapMany(){
        var nameMonoMany = fluxAndMonoGeneratorService.nameMonoFlatMapMany();

        StepVerifier.create(nameMonoMany)
                .expectNext("J","E","S","U","S")
                .verifyComplete();
    }
}