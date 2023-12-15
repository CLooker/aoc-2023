package com.clooker.aoc2023.solution.four;

import com.clooker.aoc2023.solution.Solution;
import java.util.List;

public class FourOneSolution extends Solution<Long> {

  @Override
  protected Long run(List<String> inputLines) {
    return ScratchCard
        .parseAll(inputLines)
        .stream()
        .map(scratchCard -> {
          long matchingWinningNumberCount = scratchCard
              .winningNumbers()
              .stream()
              .filter(wn -> scratchCard.numbers().contains(wn))
              .count();

          long points = 0;
          for (long mwnc = 1; mwnc <= matchingWinningNumberCount; mwnc++) {
            points = points == 0 ? 1 : (points * 2);
          }
          return points;
        })
        .mapToLong(Long::longValue)
        .sum();
  }

}

