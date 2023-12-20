package com.clooker.aoc2023.solution.six;

import java.util.List;

public class SixTwoSolution extends SixSolution {

  @Override
  protected List<BestRace> parseBestRaces(Input input) {
    List<String> inputLines = input.lines();
    String timeLine = inputLines.get(0).replace("Time:", "").trim();
    String distanceLine = inputLines.get(1).replace("Distance:", "").trim();
    String[] timeStrs = timeLine.split("\\s+");
    String[] distanceStrs = distanceLine.split("\\s+");
    return List.of(
        new BestRace(
            Long.parseLong(String.join("", timeStrs)),
            Long.parseLong(String.join("", distanceStrs))
        )
    );
  }

}

