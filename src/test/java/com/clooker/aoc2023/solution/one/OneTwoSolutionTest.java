package com.clooker.aoc2023.solution.one;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OneTwoSolutionTest {

  private final OneTwoSolution oneTwoSolution = new OneTwoSolution();

  @Test
  public void run() {
    assertEquals(281, oneTwoSolution.run("1_2-example-input.txt"));
    assertEquals(55614, oneTwoSolution.run("1-input.txt"));
  }

}