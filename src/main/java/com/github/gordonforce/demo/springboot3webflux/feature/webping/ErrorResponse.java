package com.github.gordonforce.demo.springboot3webflux.feature.webping;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.Collection;

public record ErrorResponse(
    @Schema(
            name = "remote-http-status-code",
            example = "503",
            description = "HTTP status code returned from the remote ping request",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @Min(100)
        @Max(600)
        Integer remoteHttpStatusCode,
    @Schema(name = "errors", requiredMode = Schema.RequiredMode.REQUIRED) @Valid @NotEmpty
        Collection<@Valid ErrorDetail> errors) {}
