package org.openapitools.configuration;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.net.URI;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/** Home redirection to OpenAPI api documentation */
@Controller
public class HomeController {

  @Bean
  RouterFunction<ServerResponse> index() {
    return route(
        GET("/"), req -> ServerResponse.temporaryRedirect(URI.create("swagger-ui.html")).build());
  }
}
