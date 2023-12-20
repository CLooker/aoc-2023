package com.clooker.aoc2023.solution.six;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SixTwoSolutionTest {

  private final SixTwoSolution sixTwoSolution = new SixTwoSolution();

  @Test
  public void run() {
    assertEquals(71503, sixTwoSolution.run("6-example-input.txt"));
    assertEquals(43663323, sixTwoSolution.run("6-input.txt"));
  }

}
