package com.github.gordonforce.demo.springboot3webflux.errorhandling;

public enum ErrorResponseMembers {
  CODE,
  BODY;

  public String getKey() {
    return this.name().toLowerCase();
  }
}
