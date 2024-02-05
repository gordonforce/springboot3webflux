package com.github.gordonforce.demo.springboot3webflux.feature.webping;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public record PingRemoteResponse(
    @NotNull @Min(100) @Max(600) Integer httpStatusCode,
    @Valid @NotNull @PastOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        OffsetDateTime originationDate) {}
