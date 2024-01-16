package com.github.gordonforce.demo.springboot3webflux.errorhandling;

import com.github.gordonforce.demo.springboot3webflux.api.ExecutionContexts;
import java.util.Map;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

  public GlobalErrorWebExceptionHandler(
      GlobalErrorAttributes globalErrorAttributes,
      ApplicationContext applicationContext,
      ServerCodecConfigurer serverCodecConfigurer) {
    super(globalErrorAttributes, new WebProperties.Resources(), applicationContext);
    super.setMessageWriters(serverCodecConfigurer.getWriters());
    super.setMessageReaders(serverCodecConfigurer.getReaders());
  }

  @Override
  protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
    return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
  }

  private Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {

    final Map<String, Object> errorProperties =
        getErrorAttributes(request, ErrorAttributeOptions.defaults());

    return ServerResponse.status(
            HttpStatus.valueOf((int) errorProperties.get(ErrorResponseMembers.CODE.getKey())))
        .contentType(MediaType.APPLICATION_JSON)
        .header("x-correlation-id", request.headers().header("x-correlation-id").get(0))
        .body(errorProperties.get(ErrorResponseMembers.BODY.getKey()), ExecutionContexts.class);
  }
}
