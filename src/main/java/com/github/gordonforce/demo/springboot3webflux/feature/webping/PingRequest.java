package com.github.gordonforce.demo.springboot3webflux.feature.webping;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.net.URI;

public record PingRequest(@NotNull @Valid URI uri, @NotNull HttpMethodEnum method) {}
