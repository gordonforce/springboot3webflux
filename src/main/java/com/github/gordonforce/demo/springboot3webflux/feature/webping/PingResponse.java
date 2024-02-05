package com.github.gordonforce.demo.springboot3webflux.feature.webping;

import com.github.gordonforce.demo.springboot3webflux.api.AbstractResponse;
import com.github.gordonforce.demo.springboot3webflux.api.ExecutionContexts;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.Objects;

public class PingResponse extends AbstractResponse {

  private PingRemoteResponse remoteResponse;

  public PingResponse() {
    super();
  }

  public PingResponse(
      @NotNull Boolean success,
      @NotNull ExecutionContexts executionContexts,
      @NotNull @Valid PingRemoteResponse remoteResponse) {
    super(success, executionContexts);
    this.remoteResponse = remoteResponse;
  }

  public PingRemoteResponse getRemoteResponse() {
    return remoteResponse;
  }

  public void setRemoteResponse(PingRemoteResponse remoteResponse) {
    this.remoteResponse = remoteResponse;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    PingResponse that = (PingResponse) o;
    return Objects.equals(remoteResponse, that.remoteResponse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), remoteResponse);
  }

  @Override
  public String toString() {
    return "PingResponse{" + "remoteResponse=" + remoteResponse + "} " + super.toString();
  }
}
