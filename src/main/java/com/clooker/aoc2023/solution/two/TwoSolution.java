package com.clooker.aoc2023.solution.two;

import com.clooker.aoc2023.solution.Solution;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TwoSolution extends Solution<Long> {

  @Override
  protected Long run(Input input) {
    return summarizeGames(parseGames(input))
        .stream()
        .mapToLong(Long::longValue)
        .sum();
  }

  protected abstract List<Long> summarizeGames(List<Game> games);

  private List<Game> parseGames(Input input) {
    return input
        .lines()
        .stream()
        .map(inputLine -> {
          String[] splitInputLine = inputLine.split(":");
          long gameId = Long.parseLong(splitInputLine[0].replace("Game ", ""));
          String[] countAndColorGroupsStrs = splitInputLine[1].split(";");
          List<Map<String, Integer>> colorToCountList = new ArrayList<>();
          for (String countAndColorGroupsStr : countAndColorGroupsStrs) {
            Map<String, Integer> colorToCount = new HashMap<>();
            String[] countAndColorGroups = countAndColorGroupsStr.split(",");
            for (String countAndColorGroup : countAndColorGroups) {
              String[] splitCountAndColorsGroup = countAndColorGroup.trim().split(" ");
              String countStr = splitCountAndColorsGroup[0];
              String color = splitCountAndColorsGroup[1];
              int count = Integer.parseInt(countStr);
              colorToCount.put(color, count);
            }
            colorToCountList.add(colorToCount);
          }
          return new Game(gameId, colorToCountList);
        })
        .toList();
  }

}
