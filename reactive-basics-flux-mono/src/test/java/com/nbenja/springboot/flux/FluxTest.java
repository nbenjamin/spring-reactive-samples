package com.nbenja.springboot.flux;

import static java.time.Duration.ofSeconds;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import reactor.core.publisher.Flux;


public class FluxTest {

  @Test
  public void fluxDemo_returnFlux() {
    Flux.just("Demo", "Test", "Flux").log().subscribe();
  }

  /**
   * Flux looping through list
   */
  @Test
  public void fluxDemo_loopingList_returnFlux() {
    Flux.fromIterable(asList("Demo", "Test", "Flux")).log().subscribe();
  }

  /**
   * Flux using Range
   */
  @Test
  public void fluxDemo_usingRange_returnFlux() {
    Flux.range(100, 10).log().subscribe();
  }

  /**
   * Flux using interval, but this will infinite if we dont use take()
   * however this wont support back pressure
   *
   */
  @Test
  public void fluxDemo_usingInterval_returnFlux() throws InterruptedException {
    Flux.interval(ofSeconds(2)).log().take(5).subscribe();
    SECONDS.sleep(1);
  }

  @Test
  public void fluxDemo_usingIntervalUsingRequest_returnFlux() throws InterruptedException {
    Flux.range(100, 10).log()
        .limitRate(3)
        .subscribe(System.out::println,
            null,
            null,
            null);

  }
}
