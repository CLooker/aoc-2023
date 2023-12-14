package com.clooker.aoc2023.solution.two;

import com.clooker.aoc2023.solution.Solution;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TwoOneSolution extends Solution<Long> {

  private static final Map<String, Integer> colorToCountMax = Map.of(
      "red", 12,
      "green", 13,
      "blue", 14
  );

  @Override
  protected Long run(List<String> inputLines) {
    return Game
        .parseAll(inputLines)
        .stream()
        .filter(TwoOneSolution::isGamePossible)
        .map(Game::gameId)
        .mapToLong(Long::longValue)
        .sum();
  }

  private static boolean isGamePossible(Game game) {
    return colorToCountMax
            .entrySet()
            .stream()
            .allMatch(entry -> {
              String color = entry.getKey();
              Integer countMax = entry.getValue();
              return game
                  .countToColorList()
                  .stream()
                  .map(c2c -> c2c.get(color))
                  .filter(Objects::nonNull)
                  .allMatch(count -> count <= countMax);
            });
  }
}
