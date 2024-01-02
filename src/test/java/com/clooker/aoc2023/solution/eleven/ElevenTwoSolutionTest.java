package com.clooker.aoc2023.solution.eleven;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ElevenTwoSolutionTest {

  private final ElevenTwoSolution elevenTwoSolution = new ElevenTwoSolution();

  @Test
  public void run() {
    assertEquals(779032247216L, elevenTwoSolution.run("11-input.txt"));
  }

}
