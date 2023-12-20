package com.clooker.aoc2023.solution.six;

import com.clooker.aoc2023.solution.Solution;
import java.util.List;

public abstract class SixSolution extends Solution<Long> {

  @Override
  protected Long run(Input input) {
    List<Long> recordCounts = parseBestRaces(input)
        .stream()
        .map(this::computeBestRaceImprovementCount)
        .toList();

    return recordCounts.isEmpty()
        ? 0
        : recordCounts.stream().reduce(1L, (rc1, rc2) -> rc1 * rc2);
  }

  protected abstract List<BestRace> parseBestRaces(Input input);

  private long computeBestRaceImprovementCount(BestRace bestRace) {
    long bestRaceImprovementCount = 0;
    for (int holdingTime = 1; holdingTime < bestRace.time(); holdingTime++) {
      long remainingTime = bestRace.time() - holdingTime;
      long distance = remainingTime * holdingTime;
      if (distance > bestRace.distance()) {
        bestRaceImprovementCount++;
      }
    }
    return bestRaceImprovementCount;
  }

}

