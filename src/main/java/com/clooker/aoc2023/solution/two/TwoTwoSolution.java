package com.clooker.aoc2023.solution.two;

import com.clooker.aoc2023.solution.Solution;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoTwoSolution extends Solution<Long> {

  @Override
  protected Long run(List<String> inputLines) {
    return inputLines
        .stream()
        .map(InputLine::parse)
        .map(this::extractCounts)
        .map(this::computePower)
        .reduce(0L, Long::sum);
  }

  private List<Integer> extractCounts(InputLine inputLine) {
    Map<String, Integer> colorToCount = new HashMap<>();
    inputLine
        .countToColorList()
        .forEach(c2c -> {
          c2c.forEach((color, count) ->
              colorToCount.put(
                  color,
                  colorToCount.containsKey(color)
                      ? Math.max(colorToCount.get(color), count)
                      : count
              ));
        });
    return colorToCount.values().stream().toList();
  }

  private long computePower(List<Integer> counts) {
    return counts.stream().reduce(1, (c1, c2) -> c1 * c2);
  }

}
