package com.clooker.aoc2023.solution.nine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NineOneSolutionTest {

  private final NineOneSolution nineOneSolution = new NineOneSolution();

  @Test
  public void run() {
    assertEquals(114, nineOneSolution.run("9-example-input.txt"));
    assertEquals(2008960228, nineOneSolution.run("9-input.txt"));
  }

}
