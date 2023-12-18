package com.clooker.aoc2023.solution.two;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TwoOneSolution extends TwoSolution {

  private static final Map<String, Integer> colorToCountMax = Map.of(
      "red", 12,
      "green", 13,
      "blue", 14
  );

  @Override
  protected List<Long> summarizeGames(List<Game> games) {
    return games
        .stream()
        .filter(this::isGamePossible)
        .map(Game::gameId)
        .toList();
  }

  private boolean isGamePossible(Game game) {
    return colorToCountMax
            .entrySet()
            .stream()
            .allMatch(entry -> {
              String color = entry.getKey();
              Integer countMax = entry.getValue();
              return game
                  .colorToCountList()
                  .stream()
                  .map(c2c -> c2c.get(color))
                  .filter(Objects::nonNull)
                  .allMatch(count -> count <= countMax);
            });
  }

}
