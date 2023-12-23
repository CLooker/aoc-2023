package com.clooker.aoc2023.solution.nine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NineTwoSolutionTest {

  private final NineTwoSolution nineTwoSolution = new NineTwoSolution();

  @Test
  public void run() {
    assertEquals(2, nineTwoSolution.run("9-example-input.txt"));
    assertEquals(1097, nineTwoSolution.run("9-input.txt"));
  }

}
