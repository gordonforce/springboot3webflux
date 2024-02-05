package com.github.gordonforce.demo.springboot3webflux.errorhandling;

import com.github.gordonforce.demo.springboot3webflux.api.ExecutionContextsBuilder;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler({
    ConstraintViolationException.class,
    MissingRequestValueException.class,
    MethodArgumentTypeMismatchException.class
  })
  public ResponseEntity<ErrorResponse> badRequests(final RuntimeException rex) {
    return ResponseEntity.badRequest()
        .body(
            new ErrorResponse(
                false,
                ExecutionContextsBuilder.of()
                    .addThrowable(
                        Integer.toString(400), "Invalid or missing request data", "WebPing", rex)
                    .build()));
  }
}
