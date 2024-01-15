package com.github.gordonforce.demo.springboot3webflux.feature.webping;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.net.URI;

public record PingRequest(
    @Schema(
            name = "uri",
            example = "https://www.google.com",
            description = "URL to ping",
            requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        @Valid
        URI uri,
    @Schema(
            name = "method",
            example = "GET",
            description = "HTTP method to use",
            requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        HttpMethodEnum method) {}
