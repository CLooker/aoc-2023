package com.clooker.aoc2023.solution.two;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TwoTwoSolutionTest {

  private final TwoTwoSolution twoTwoSolution = new TwoTwoSolution();

  @Test
  public void run() {
    assertEquals(2286, twoTwoSolution.run("2-example-input.txt"));
    assertEquals(83105, twoTwoSolution.run("2-input.txt"));
  }

}