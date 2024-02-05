package com.github.gordonforce.demo.springboot3webflux.feature.webping;

import com.github.gordonforce.demo.springboot3webflux.api.ExecutionContext;
import com.github.gordonforce.demo.springboot3webflux.api.ExecutionContextsBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Validated
public class WebPingController {

  private final PingRemoteService pingRemoteService;

  public WebPingController(PingRemoteService pingRemoteService) {
    this.pingRemoteService = pingRemoteService;
  }

  /**
   * POST /ping : Ping
   *
   * @param xCorrelationId (required)
   * @param pingRequest (required)
   * @param xPostDelayMs the number of milliseconds to delay after processing the request (optional)
   * @param xPreDelayMs the number of milliseconds to delay before processing the request (optional)
   * @return OK (status code 200) or Bad Request (status code 400) or Service Unavailable (status
   *     code 503)
   */
  @PostMapping(
      value = "/ping",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ResponseEntity<PingResponse>> ping(
      @RequestHeader(value = "x-correlation-id") @NotBlank final String xCorrelationId,
      @NotNull @Valid @RequestBody Mono<PingRequest> pingRequest,
      final @RequestHeader(value = "x-post-delay-ms", required = false, defaultValue = "0") int
              xPostDelayMs,
      final @RequestHeader(value = "x-pre-delay-ms", required = false, defaultValue = "0") int
              xPreDelayMs) {

    return pingRequest
        .flatMap(
            request ->
                pingRemoteService.pingRemote(
                    request,
                    Duration.of(xPreDelayMs, ChronoUnit.MILLIS),
                    Duration.of(xPostDelayMs, ChronoUnit.MILLIS)))
        .map(
            remoteResponse ->
                new PingResponse(
                    true,
                    ExecutionContextsBuilder.of()
                        .addInfo(new ExecutionContext("200", "ping successful", "WebPing"))
                        .build(),
                    remoteResponse))
        .map(
            response ->
                ResponseEntity.ok().header("x-correlation-id", xCorrelationId).body(response));
  }
}
