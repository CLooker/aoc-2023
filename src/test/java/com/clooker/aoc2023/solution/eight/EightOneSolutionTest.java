package com.clooker.aoc2023.solution.eight;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EightOneSolutionTest {

  private final EightOneSolution eightOneSolution = new EightOneSolution();

  @Test
  public void run() {
    assertEquals(6, eightOneSolution.run("8_1-example-input.txt"));
    assertEquals(21409, eightOneSolution.run("8-input.txt"));
  }

}
