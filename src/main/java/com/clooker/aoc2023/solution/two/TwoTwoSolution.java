package com.clooker.aoc2023.solution.two;

import com.clooker.aoc2023.solution.Solution;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoTwoSolution extends Solution<Long> {

  @Override
  protected Long run(List<String> inputLines) {
    return Game
        .parseAll(inputLines)
        .stream()
        .map(TwoTwoSolution::computeColorToCountMin)
        .map(TwoTwoSolution::computePower)
        .mapToLong(Long::longValue)
        .sum();
  }

  private static Map<String, Integer> computeColorToCountMin(Game game) {
    Map<String, Integer> colorToCount = new HashMap<>();
    game
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
    return colorToCount;
  }

  private static Long computePower(Map<String, Integer> colorToCount) {
    return colorToCount
        .values()
        .stream()
        .mapToLong(Integer::longValue)
        .reduce(1L, (c1, c2) -> c1 * c2);
  }

}
