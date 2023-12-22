package com.clooker.aoc2023.solution.eight;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EightTwoSolutionTest {

  private final EightTwoSolution eightTwoSolution = new EightTwoSolution();

  @Test
  public void run() {
    assertEquals(6, eightTwoSolution.run("8_2-example-input.txt"));
    assertEquals(21165830176709L, eightTwoSolution.run("8-input.txt"));
  }

}
