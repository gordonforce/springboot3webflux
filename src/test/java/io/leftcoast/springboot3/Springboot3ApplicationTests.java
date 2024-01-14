package io.leftcoast.springboot3;

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
