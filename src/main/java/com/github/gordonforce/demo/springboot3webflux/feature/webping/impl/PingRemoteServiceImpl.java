package com.github.gordonforce.demo.springboot3webflux.feature.webping.impl;

import com.github.gordonforce.demo.springboot3webflux.feature.webping.*;
import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PingRemoteServiceImpl implements PingRemoteService {

  private final PingRemoteEndpoint pingRemoteEndpoint;

  private static final Map<HttpMethodEnum, HttpMethod> HTTP_METHOD_MAP =
      new EnumMap<>(
          Map.of(
              HttpMethodEnum.GET, HttpMethod.GET,
              HttpMethodEnum.POST, HttpMethod.POST,
              HttpMethodEnum.PUT, HttpMethod.PUT,
              HttpMethodEnum.DELETE, HttpMethod.DELETE,
              HttpMethodEnum.PATCH, HttpMethod.PATCH));

  public PingRemoteServiceImpl(PingRemoteEndpoint pingRemoteEndpoint) {
    this.pingRemoteEndpoint = pingRemoteEndpoint;
  }

  @Override
  public Mono<PingRemoteResponse> pingRemote(
      final PingRequest pingRequest, final Duration preDelay, final Duration postDelay) {

    return Mono.delay(preDelay)
        .then(
            pingRemoteEndpoint.pingRemoteEndpoint(
                HTTP_METHOD_MAP.getOrDefault(pingRequest.method(), HttpMethod.GET),
                pingRequest.uri()))
        .delayElement(postDelay)
        .map(
            rsp ->
                new PingRemoteResponse(
                    rsp.getStatusCode().value(),
                    Optional.of(rsp.getHeaders())
                        .map(
                            h ->
                                Instant.ofEpochMilli(h.getFirstDate(HttpHeaders.DATE))
                                    .atZone(ZoneId.systemDefault()))
                        .map(OffsetDateTime::from)
                        .orElseGet(OffsetDateTime::now)));
  }
}
