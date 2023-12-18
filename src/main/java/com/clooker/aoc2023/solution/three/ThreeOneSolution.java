package com.clooker.aoc2023.solution.three;

import java.util.List;

public class ThreeOneSolution extends ThreeSolution {

  @Override
  protected List<Integer> summarizeInput(Input input) {
    return InputParser
        .parsePartNumbers(input)
        .stream()
        .map(PartNumber::value)
        .toList();
  }

}
