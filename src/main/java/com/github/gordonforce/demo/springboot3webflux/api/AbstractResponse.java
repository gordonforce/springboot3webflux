package com.github.gordonforce.demo.springboot3webflux.api;

import java.util.Objects;

public abstract class AbstractResponse {

  private Boolean success;

  private ExecutionContexts executionContexts;

  protected AbstractResponse() {}

  protected AbstractResponse(Boolean success, ExecutionContexts executionContexts) {
    this.success = success;
    this.executionContexts = executionContexts;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public ExecutionContexts getExecutionContexts() {
    return executionContexts;
  }

  public void setExecutionContexts(ExecutionContexts executionContexts) {
    this.executionContexts = executionContexts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractResponse that = (AbstractResponse) o;
    return Objects.equals(success, that.success)
        && Objects.equals(executionContexts, that.executionContexts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, executionContexts);
  }

  @Override
  public String toString() {
    return "AbstractResponse{"
        + "success="
        + success
        + ", executionContexts="
        + executionContexts
        + '}';
  }
}
