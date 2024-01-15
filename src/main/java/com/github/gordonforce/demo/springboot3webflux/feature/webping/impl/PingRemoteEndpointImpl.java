package com.github.gordonforce.demo.springboot3webflux.feature.webping.impl;

import com.github.gordonforce.demo.springboot3webflux.feature.webping.PingRemoteEndpoint;
import java.net.URI;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PingRemoteEndpointImpl implements PingRemoteEndpoint {
  @Override
  public Mono<ResponseEntity<String>> pingRemoteEndpoint(HttpMethod httpMethod, URI uri) {
    return WebClient.builder()
        .build()
        .method(httpMethod)
        .uri(uri)
        .retrieve()
        .toEntity(String.class);
  }
}
