package com.github.gordonforce.demo.springboot3webflux.errorhandling;

import jakarta.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

record ExceptionRule(Class<?> exceptionClass, HttpStatus status) {}

// @Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

  private final List<ExceptionRule> exceptionsRules =
      List.of(new ExceptionRule(ConstraintViolationException.class, HttpStatus.BAD_REQUEST));

  @Override
  public Map<String, Object> getErrorAttributes(
      final ServerRequest request, final ErrorAttributeOptions options) {

    final Throwable error = getError(request);

    return Collections.emptyMap();
    /*
       return exceptionsRules.stream()
           .map(
               exceptionRule ->
                   exceptionRule.exceptionClass().isInstance(error) ? exceptionRule : null)
           .filter(Objects::nonNull)
           .findFirst()
           .map(
               exceptionRule ->
                   Map.of(
                       ErrorResponseMembers.CODE.getKey(),
                       exceptionRule.status().value(),
                       ErrorResponseMembers.BODY.getKey(),
                       ExecutionContextsBuilder.of().addThrowable("500", "WebPing", error).build()))
           .orElseGet(
               () ->
                   Map.of(
                       ErrorResponseMembers.CODE.getKey(),
                       determineHttpStatus(error).value(),
                       ErrorResponseMembers.BODY.getKey(),
                       ExecutionContextsBuilder.of().build()));

    */
  }

  private HttpStatus determineHttpStatus(Throwable error) {
    return error instanceof ResponseStatusException err
        ? HttpStatus.valueOf(err.getStatusCode().value())
        : MergedAnnotations.from(error.getClass(), MergedAnnotations.SearchStrategy.TYPE_HIERARCHY)
            .get(ResponseStatus.class)
            .getValue(ErrorResponseMembers.CODE.getKey(), HttpStatus.class)
            .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
