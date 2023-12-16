package com.clooker.aoc2023.solution.five;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FiveOneSolutionTest {

  private final FiveOneSolution fiveOneSolution = new FiveOneSolution();

  @Test
  public void run() {
    assertEquals(35, fiveOneSolution.run("5-example-input.txt"));
    assertEquals(346433842, fiveOneSolution.run("5-input.txt"));
  }

}
