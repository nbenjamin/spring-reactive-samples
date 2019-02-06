package com.nbenja.springboot.operator;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OperatorTest {

  @Test
  public void operatorMap_test() {
    Flux.range(1,3)
        .map(i ->  i *10)
        .log().subscribe();
  }


  @Test
  public void operatorFlatMap_test() {
    Flux.range(1,3)
        .flatMap(i ->  Flux.range(i *10, 2))
        .log().subscribe();
  }


  @Test
  public void operatorFlatMapMany_test() {
    Mono.just(3)
        .flatMapMany(i ->  Flux.range(i *10, i))
        .log().subscribe();
  }

  @Test
  public void operatorConcateOrMerge_test() {
    Flux.concat(Flux.range(1,3), Flux.range(10,3))
        .log().subscribe();


    Flux.range(1,3).mergeWith(Flux.range(10,3)).log().subscribe();
  }

  @Test
  public void operatorZip_test() {
    Flux<String> firstName = Flux.just("Tom", "Ryan", "Adam");
    Flux<String> lastName = Flux.just("Jerry", "Ryan");

    Flux.zip(firstName, lastName)
        .log().subscribe();

    Flux.zip(firstName, lastName, (integer, integer2) -> integer + ", " + integer2)
        .log().subscribe(System.out::println);
    }
}
