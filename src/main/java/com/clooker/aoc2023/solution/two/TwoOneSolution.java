package com.clooker.aoc2023.solution.two;

import com.clooker.aoc2023.solution.Solution;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TwoOneSolution extends Solution<Long> {

  private final Map<String, Integer> colorToCount = Map.of(
      "red", 12,
      "green", 13,
      "blue", 14
  );

  @Override
  protected Long run(List<String> inputLines) {
    return inputLines
        .stream()
        .map(InputLine::parse)
        .filter(this::isPossibleGame)
        .map(InputLine::id)
        .reduce(0L, Long::sum);
  }

  private boolean isPossibleGame(InputLine inputLine) {
    return colorToCount
            .entrySet()
            .stream()
            .allMatch(entry -> {
              String color = entry.getKey();
              Integer count = entry.getValue();
              return inputLine
                  .countToColorList()
                  .stream()
                  .map(c2c -> c2c.get(color))
                  .filter(Objects::nonNull)
                  .allMatch(ct -> ct <= count);
            });
  }

}
