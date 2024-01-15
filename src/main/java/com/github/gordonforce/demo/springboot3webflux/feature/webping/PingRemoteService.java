package com.github.gordonforce.demo.springboot3webflux.feature.webping;

import java.time.Duration;
import reactor.core.publisher.Mono;

public interface PingRemoteService {

  Mono<PingResponse> pingRemote(PingRequest pingRequest, Duration preDelay, Duration postDelay);
}
