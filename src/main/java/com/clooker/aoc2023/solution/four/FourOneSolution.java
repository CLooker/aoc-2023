package com.clooker.aoc2023.solution.four;

import com.clooker.aoc2023.solution.Solution;

public class FourOneSolution extends Solution<Long> {

  @Override
  protected Long run(Input input) {
    return InputParser
        .parseScratchCards(input)
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

