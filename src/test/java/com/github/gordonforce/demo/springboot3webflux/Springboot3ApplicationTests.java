package com.github.gordonforce.demo.springboot3webflux;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot3ApplicationTests {

  @Test
  void contextLoads() {
    final boolean passes = true;
    assertThat(passes).isTrue();
  }
}
