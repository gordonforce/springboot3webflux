package com.github.gordonforce.demo.springboot3webflux.feature.webping;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public record PingRemoteResponse(
    @Schema(
            name = "httpStatusCode",
            example = "503",
            description = "HTTP status code returned from the remote ping request",
            requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        @Min(100)
        @Max(600)
        Integer httpStatusCode,
    @Schema(
            name = "originationDate",
            example = "2024-01-01T12:00-08:00",
            description = "origination local time stamp",
            requiredMode = Schema.RequiredMode.REQUIRED)
        @Valid
        @NotNull
        @PastOrPresent
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        OffsetDateTime originationDate) {}
