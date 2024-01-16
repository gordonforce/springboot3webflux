package com.github.gordonforce.demo.springboot3webflux.errorhandling;

import com.github.gordonforce.demo.springboot3webflux.api.AbstractResponse;
import com.github.gordonforce.demo.springboot3webflux.api.ExecutionContexts;
import jakarta.validation.constraints.*;

public class ErrorResponse extends AbstractResponse {

  public ErrorResponse(@NotNull Boolean success, @NotNull ExecutionContexts executionContexts) {
    super(success, executionContexts);
  }
}
