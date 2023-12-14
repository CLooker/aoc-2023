package com.clooker.aoc2023.solution.three;

import com.clooker.aoc2023.solution.Solution;
import java.util.List;

public class ThreeTwoSolution extends Solution<Integer> {

  @Override
  protected Integer run(List<String> inputLines) {
    return GearRatio
        .parseAll(inputLines)
        .stream()
        .map(GearRatio::value)
        .mapToInt(Integer::intValue)
        .sum();
  }

}
