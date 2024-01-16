package com.github.gordonforce.demo.springboot3webflux.feature.webping.impl;

import com.github.gordonforce.demo.springboot3webflux.feature.webping.PingRemoteEndpoint;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PingRemoteEndpointImpl implements PingRemoteEndpoint {

  private static final Logger LOGGER = LoggerFactory.getLogger(PingRemoteEndpointImpl.class);

  @Override
  public Mono<ResponseEntity<String>> pingRemoteEndpoint(HttpMethod httpMethod, URI uri) {

    LOGGER.debug("pingRemoteEndpoint: httpMethod={}, uri={}", httpMethod, uri);

    return WebClient.builder()
        .build()
        .method(httpMethod)
        .uri(uri)
        .retrieve()
        .toEntity(String.class)
        .log();
  }
}
