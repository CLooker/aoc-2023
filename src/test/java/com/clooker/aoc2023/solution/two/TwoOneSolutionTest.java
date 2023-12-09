package com.clooker.aoc2023.solution.two;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TwoOneSolutionTest {

  private final TwoOneSolution twoOneSolution = new TwoOneSolution();

  @Test
  public void run() {
    assertEquals(8, twoOneSolution.run("2-example-input.txt"));
    assertEquals(1931, twoOneSolution.run("2-input.txt"));
  }

}