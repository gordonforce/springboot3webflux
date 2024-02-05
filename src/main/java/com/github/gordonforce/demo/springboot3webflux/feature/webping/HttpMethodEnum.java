package com.github.gordonforce.demo.springboot3webflux.feature.webping;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;

public enum HttpMethodEnum {
  DELETE("delete"),

  GET("get"),

  HEAD("head"),

  OPTIONS("options"),

  PATCH("patch"),

  POST("post"),

  PUT("put");

  private final String value;

  HttpMethodEnum(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static HttpMethodEnum fromValue(final String value) {

    return Arrays.stream(HttpMethodEnum.values())
        .filter(v -> value.equalsIgnoreCase(v.getValue()))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unexpected value '" + value + "'"));
  }
}
