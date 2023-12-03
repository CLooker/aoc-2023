package com.clooker.aoc2023.solution.one;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OneOneSolutionTest {

  private final OneOneSolution oneOneSolution = new OneOneSolution();

  @Test
  public void run() {
    assertEquals(142, oneOneSolution.run("1_1-example-input.txt"));
    assertEquals(55488, oneOneSolution.run("1-input.txt"));
  }

}