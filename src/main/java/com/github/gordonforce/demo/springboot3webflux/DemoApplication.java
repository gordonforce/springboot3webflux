package com.github.gordonforce.demo.springboot3webflux;

import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
@ComponentScan(
    basePackages = {
      "com.github.gordonforce.demo.springboot3webflux",
      "org.openapitools.configuration"
    },
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public com.fasterxml.jackson.databind.Module jsonNullableModule() {
    return new JsonNullableModule();
  }
}
