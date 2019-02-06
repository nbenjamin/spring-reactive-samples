package com.nbenja.springboot.mono;

import java.util.concurrent.ExecutionException;
import org.junit.Test;
import reactor.core.publisher.Mono;

public class MonoTest {

  @Test
  public void monoDemo_returnMono() {
    Mono.just("A").log().subscribe();
  }

  @Test
  public void monoDemo_consumer_returnMono() {
    Mono.just("Demo").log().subscribe(System.out::println);
  }

  @Test
  public void monoDemo_withDoOnActions_returnMono() {
    Mono.just("Demo").log()
        .doOnSubscribe(System.out::println)
        .doOnRequest(System.out::println)
        // Print "A" 3 times now, onSuccess, onNext and Subscribe
        .doOnSuccess(System.out::println)
        .doOnNext(System.out::println)
        .subscribe(System.out::println);
  }

  @Test
  public void monoDemo_withEmpty_returnMono() {
    // user for void
    Mono.empty().log()
        .subscribe(System.out::println,
            null,
            () -> System.out.println("Done"));
  }

  /**
   * Return exception on Mono
   */
  @Test
  public void monoDemo_withException_returnMono() {
    // user for void
    Mono.error(new Exception())
        .log()
        .subscribe(System.out::println,
            System.out::println,
            () -> System.out.println("Done"));
  }

  /**
   * Do action on Error
   */
  @Test
  public void monoDemo_withException_doOnError() {
    // user for void
    Mono.error(new Exception())
        .log()
        .doOnError(e -> System.out.println(" On Error - "+ e))
        .subscribe(r-> System.out.println("Result - " + r),
            e -> System.out.println("Done  "+ e),
            () -> System.out.println("Done"));
  }

  /**
   * onErrorResume is useful if you want to return Mono on any exception
   */
  @Test
  public void monoDemo_withException_doOnResumeAndReturnDefaultMono() {
    // user for void
    Mono.error(new Exception())
        .log()
        .onErrorResume(t -> Mono.just("OnFailure"))
        .subscribe(r-> System.out.println("Result - " + r),
            e -> System.out.println("Done  With Exception"+ e),
            () -> System.out.println("Done"));
  }

  /**
   * onErrorReturn can return a value.
   */
  @Test
  public void monoDemo_withException_onErrorReturnDefaultMono() {
    // user for void
    Mono.error(new Exception())
        .log()
        .onErrorReturn("OnFailure")
        .subscribe(r-> System.out.println("Result - " + r),
            e -> System.out.println("Done  With Exception"+ e),
            () -> System.out.println("Done"));
  }
}
