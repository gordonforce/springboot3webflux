package com.github.gordonforce.demo.springboot3webflux.api;

import jakarta.validation.constraints.NotNull;
import java.util.Collection;

public record ExecutionContexts(
    @NotNull Collection<ExecutionContext> infos,
    @NotNull Collection<ExecutionContext> warnings,
    @NotNull Collection<ExecutionContext> errors) {}
