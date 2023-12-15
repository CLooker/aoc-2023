package com.clooker.aoc2023.solution.four;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FourTwoSolutionTest {

  private final FourTwoSolution fourTwoSolution = new FourTwoSolution();

  @Test
  public void run() {
    assertEquals(30, fourTwoSolution.run("4-example-input.txt"));
    assertEquals(8570000, fourTwoSolution.run("4-input.txt"));
  }

}
