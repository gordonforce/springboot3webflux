package com.github.gordonforce.demo.springboot3webflux.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record ExecutionContext(
    @Schema(
            name = "code",
            example = "could not find hostname",
            description = "a code describing the type of error",
            requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String code,
    @Schema(
            name = "message",
            example = "Bad Request",
            description = "a human readable error message",
            requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String message,
    @Schema(
            name = "system",
            example = "www.google.com, or web-ping",
            description = "The name of the system or host that generated the error",
            requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String system) {}
