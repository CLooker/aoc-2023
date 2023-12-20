package com.clooker.aoc2023.solution.six;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SixOneSolutionTest {

  private final SixOneSolution sixOneSolution = new SixOneSolution();

  @Test
  public void run() {
    assertEquals(288, sixOneSolution.run("6-example-input.txt"));
    assertEquals(625968, sixOneSolution.run("6-input.txt"));
  }

}
