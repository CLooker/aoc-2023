package com.clooker.aoc2023.solution.four;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FourOneSolutionTest {

  private final FourOneSolution fourOneSolution = new FourOneSolution();

  @Test
  public void run() {
    assertEquals(13, fourOneSolution.run("4-example-input.txt"));
    assertEquals(23847, fourOneSolution.run("4-input.txt"));
  }

}
