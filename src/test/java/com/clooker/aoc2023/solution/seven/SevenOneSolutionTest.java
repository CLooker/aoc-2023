package com.clooker.aoc2023.solution.seven;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SevenOneSolutionTest {

  private final SevenOneSolution sevenOneSolution = new SevenOneSolution();

  @Test
  public void run() {
    assertEquals(6440, sevenOneSolution.run("7-example-input.txt"));
    assertEquals(250058342, sevenOneSolution.run("7-input.txt"));
  }

}
