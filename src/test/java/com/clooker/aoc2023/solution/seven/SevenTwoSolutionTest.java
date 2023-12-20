package com.clooker.aoc2023.solution.seven;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SevenTwoSolutionTest {

  private final SevenTwoSolution sevenTwoSolution = new SevenTwoSolution();

  @Test
  public void run() {
    assertEquals(5905, sevenTwoSolution.run("7-example-input.txt"));
    assertEquals(250506580, sevenTwoSolution.run("7-input.txt"));
  }

}
