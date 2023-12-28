package com.clooker.aoc2023.solution.ten;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TenTwoSolutionTest {

  private final TenTwoSolution tenTwoSolution = new TenTwoSolution();

  @Test
  public void run() {
    assertEquals(10, tenTwoSolution.run("10_2-example-input.txt"));
    assertEquals(459, tenTwoSolution.run("10-input.txt"));
  }

}
