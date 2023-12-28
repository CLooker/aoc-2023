package com.clooker.aoc2023.solution.ten;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TenOneSolutionTest {

  private final TenOneSolution tenOneSolution = new TenOneSolution();

  @Test
  public void run() {
    assertEquals(4, tenOneSolution.run("10_1-example-input.txt"));
    assertEquals(8, tenOneSolution.run("10_1-example-input-v2.txt"));
    assertEquals(6828, tenOneSolution.run("10-input.txt"));
  }

}
