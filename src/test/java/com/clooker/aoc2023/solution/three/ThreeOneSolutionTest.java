package com.clooker.aoc2023.solution.three;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ThreeOneSolutionTest {

  private final ThreeOneSolution threeOneSolution = new ThreeOneSolution();

  @Test
  public void run() {
    assertEquals(4361, threeOneSolution.run("3-example-input.txt"));
    assertEquals(549908, threeOneSolution.run("3-input.txt"));
  }

}