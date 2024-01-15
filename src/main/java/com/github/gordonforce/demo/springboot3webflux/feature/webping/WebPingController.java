package com.github.gordonforce.demo.springboot3webflux.feature.webping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Tag(name = "ping", description = "the ping API")
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
  @Operation(
      operationId = "ping",
      summary = "Ping",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = PingResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "503",
            description = "Service Unavailable",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @PostMapping(
      value = "/ping",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ResponseEntity<Mono<PingResponse>>> ping(
      @NotNull
          @Parameter(
              name = "x-correlation-id",
              description = "a unique request identifier provider by the caller",
              required = true,
              in = ParameterIn.HEADER)
          @RequestHeader(value = "x-correlation-id")
          UUID xCorrelationId,
      @Parameter(name = "PingRequest", description = "what and how to ping") @Valid @RequestBody
          PingRequest pingRequest,
      @Parameter(
              name = "x-post-delay-ms",
              description = "the number of milliseconds to to delay after processing the request",
              in = ParameterIn.HEADER)
          @RequestHeader(value = "x-post-delay-ms", required = false)
          Optional<Integer> xPostDelayMs,
      @Parameter(
              name = "x-pre-delay-ms",
              description = "the number of milliseconds to delay before processing the request",
              in = ParameterIn.HEADER)
          @RequestHeader(value = "x-pre-delay-ms", required = false)
          Optional<Integer> xPreDelayMs) {

    return Mono.just(
        ResponseEntity.ok()
            .header("x-correlation-id", xCorrelationId.toString())
            .body(
                pingRemoteService.pingRemote(
                    pingRequest,
                    Duration.of(xPreDelayMs.orElse(0), ChronoUnit.MILLIS),
                    Duration.of(xPostDelayMs.orElse(0), ChronoUnit.MILLIS))));
  }
}
