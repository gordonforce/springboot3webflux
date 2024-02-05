package com.github.gordonforce.demo.springboot3webflux.api;

import jakarta.validation.constraints.NotBlank;

public record ExecutionContext(
    @NotBlank String code, @NotBlank String message, @NotBlank String system) {}
