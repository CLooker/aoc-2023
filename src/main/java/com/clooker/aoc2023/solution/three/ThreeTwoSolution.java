package com.clooker.aoc2023.solution.three;

import java.util.List;

public class ThreeTwoSolution extends ThreeSolution {

  @Override
  protected List<Integer> summarizeInput(Input input) {
    return InputParser
        .parseGearRatios(input)
        .stream()
        .map(GearRatio::value)
        .toList();
  }

}
