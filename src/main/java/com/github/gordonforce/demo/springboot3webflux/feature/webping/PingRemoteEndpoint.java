package com.github.gordonforce.demo.springboot3webflux.feature.webping;

import java.net.URI;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface PingRemoteEndpoint {

  Mono<ResponseEntity<String>> pingRemoteEndpoint(HttpMethod httpMethod, URI uri);
}
