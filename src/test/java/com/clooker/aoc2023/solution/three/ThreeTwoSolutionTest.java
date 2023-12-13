package com.clooker.aoc2023.solution.three;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ThreeTwoSolutionTest {

  private final ThreeTwoSolution threeTwoSolution = new ThreeTwoSolution();

  @Test
  public void run() {
//    assertEquals(467835, threeTwoSolution.run("3-example-input.txt"));
    assertEquals(81166799, threeTwoSolution.run("3-input.txt"));
  }

}