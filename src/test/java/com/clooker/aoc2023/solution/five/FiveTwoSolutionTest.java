package com.clooker.aoc2023.solution.five;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FiveTwoSolutionTest {

  private final FiveTwoSolution fiveTwoSolution = new FiveTwoSolution();

  @Test
  public void run() {
    assertEquals(46, fiveTwoSolution.run("5-example-input.txt"));
    assertEquals(60294664, fiveTwoSolution.run("5-input.txt"));
  }

}
