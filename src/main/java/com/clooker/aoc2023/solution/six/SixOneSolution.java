package com.clooker.aoc2023.solution.six;

import java.util.ArrayList;
import java.util.List;

public class SixOneSolution extends SixSolution {

  @Override
  protected List<BestRace> parseBestRaces(Input input) {
    List<String> inputLines = input.lines();
    String timeLine = inputLines.get(0).replace("Time:", "").trim();
    String distanceLine = inputLines.get(1).replace("Distance:", "").trim();
    String[] timeStrs = timeLine.split("\\s+");
    String[] distanceStrs = distanceLine.split("\\s+");
    List<BestRace> bestRaces = new ArrayList<>();
    for (int i = 0; i < timeStrs.length; i++) {
      long time = Long.parseLong(timeStrs[i]);
      long distance = Long.parseLong(distanceStrs[i]);
      bestRaces.add(new BestRace(time, distance));
    }
    return bestRaces;
  }

}

