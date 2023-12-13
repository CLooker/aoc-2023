package com.clooker.aoc2023.solution.three;

import com.clooker.aoc2023.solution.Solution;
import java.util.List;

public class ThreeOneSolution extends Solution<Integer> {

  @Override
  protected Integer run(List<String> input) {
    return InputParser
        .partNumbers(input)
        .stream()
        .map(PartNumber::value)
        .mapToInt(Integer::intValue)
        .sum();
  }

}
