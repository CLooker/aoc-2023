package com.clooker.aoc2023.solution.three;

import com.clooker.aoc2023.solution.Solution;
import java.util.List;

public abstract class ThreeSolution extends Solution<Integer> {

  @Override
  protected Integer run(Input input) {
    return summarizeInput(input)
        .stream()
        .mapToInt(Integer::intValue)
        .sum();
  }

  protected abstract List<Integer> summarizeInput(Input input);

}
