package com.github.gordonforce.demo.springboot3webflux.api;

public class ExecutionContextsBuilder {

  private ExecutionContextsBuilder() {}

  private final CollectionBuilder<ExecutionContext> infosBuilder = new CollectionBuilder<>();

  private final CollectionBuilder<ExecutionContext> warningsBuilder = new CollectionBuilder<>();

  private final CollectionBuilder<ExecutionContext> errorsBuilder = new CollectionBuilder<>();

  public ExecutionContextsBuilder addError(ExecutionContext error) {
    errorsBuilder.add(error);
    return this;
  }

  public ExecutionContextsBuilder addInfo(ExecutionContext info) {
    infosBuilder.add(info);
    return this;
  }

  public ExecutionContextsBuilder addWarning(ExecutionContext warning) {
    warningsBuilder.add(warning);
    return this;
  }

  public ExecutionContextsBuilder addThrowable(
      String code, String messagePrefix, String system, Throwable throwable) {

    errorsBuilder.add(
        new ExecutionContext(
            code, String.join(": ", messagePrefix, throwable.getMessage()), system));

    if (throwable.getCause() != null) {
      addThrowable(code, messagePrefix, system, throwable.getCause());
    }

    return this;
  }

  public ExecutionContexts build() {
    return new ExecutionContexts(
        infosBuilder.build(), warningsBuilder.build(), errorsBuilder.build());
  }

  public static ExecutionContextsBuilder of() {
    return new ExecutionContextsBuilder();
  }
}
