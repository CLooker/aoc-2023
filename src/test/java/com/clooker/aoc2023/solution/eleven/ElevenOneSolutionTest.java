package com.clooker.aoc2023.solution.eleven;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ElevenOneSolutionTest {

  private final ElevenOneSolution elevenOneSolution = new ElevenOneSolution();

  @Test
  public void run() {
    assertEquals(374, elevenOneSolution.run("11-example-input.txt"));
    assertEquals(9805264, elevenOneSolution.run("11-input.txt"));
  }

}
