package com.clooker.aoc2023.solution.two;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoTwoSolution extends TwoOneSolution {

  @Override
  protected List<Long> summarizeGames(List<Game> games) {
    return games
        .stream()
        .map(this::computeColorToMaxCount)
        .map(this::computePower)
        .toList();
  }

  private Map<String, Integer> computeColorToMaxCount(Game game) {
    Map<String, Integer> colorToCount = new HashMap<>();
    game
        .colorToCountList()
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

  private Long computePower(Map<String, Integer> colorToCount) {
    return colorToCount
        .values()
        .stream()
        .mapToLong(Integer::longValue)
        .reduce(1L, (c1, c2) -> c1 * c2);
  }

}
