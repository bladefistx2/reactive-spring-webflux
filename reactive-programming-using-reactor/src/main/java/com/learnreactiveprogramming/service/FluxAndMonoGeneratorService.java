package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux(){
//        return Flux.fromIterable(List.of("James", "Mary", "Moses"));
        return Flux.just("James", "Mary", "Moses").log();
    }

    public Flux<String> namesFluxMap(int minStringLen){
        return Flux.just("James", "Mary", "Moses")
                .map(String::toUpperCase)
                .filter(s -> s.length() >= minStringLen)
                .map(s -> s.length() + "-" + s)
                .log();
    }

    public Flux<String> namesFluxFlatMap(){
        return Flux.just("James", "Mary", "Moses")
                .map(String::toUpperCase)
                .flatMap(s -> Flux.fromArray(s.split("")))
                .log();
    }

    public Mono<String> nameMono(){
        return Mono.just("Jesus").log();
    }

    public Mono<List<String>> nameMonoFlatMap(){
        return Mono.just("Jesus")
                .map(String::toUpperCase)
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    public Flux<String> nameMonoFlatMapMany(){
        return Mono.just("Jesus")
                .map(String::toUpperCase)
                .flatMapMany(s -> Flux.fromArray(s.split("")))
                .log();
    }

    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
        fluxAndMonoGeneratorService.namesFlux().subscribe(name -> System.out.println("name: " + name));

        fluxAndMonoGeneratorService.nameMono().subscribe(name -> System.out.println("name: " + name));
    }
}
