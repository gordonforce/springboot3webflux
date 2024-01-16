package com.github.gordonforce.demo.springboot3webflux.feature.webping;

import java.time.Duration;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

public interface PingRemoteService {

  @NonNull
  Mono<PingRemoteResponse> pingRemote(
      PingRequest pingRequest, Duration preDelay, Duration postDelay);
}
