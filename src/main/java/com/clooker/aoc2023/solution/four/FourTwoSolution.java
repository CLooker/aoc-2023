package com.clooker.aoc2023.solution.four;

import com.clooker.aoc2023.solution.Solution;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FourTwoSolution extends Solution<Long> {

  @Override
  protected Long run(List<String> inputLines) {
    Map<Integer, Integer> scratchCardIdToMatchingCount = ScratchCard
        .parseAll(inputLines)
        .stream()
        .map(scratchCard -> {
          int matchingCount = (int) scratchCard
              .winningNumbers()
              .stream()
              .filter(wn -> scratchCard.numbers().contains(wn))
              .count();

          return Map.entry(scratchCard.scratchCardId(), matchingCount);
        })
        .collect(
            Collectors.toMap(
                Entry::getKey,
                Entry::getValue
            )
        );

    Queue<Integer> scratchCardIds = scratchCardIdToMatchingCount
        .entrySet()
        .stream()
        .flatMapToInt(entry -> {
          int scratchCardId = entry.getKey();
          int matchingCount = entry.getValue();
          return IntStream.range(scratchCardId + 1, scratchCardId + matchingCount + 1);
        })
        .boxed()
        .collect(Collectors.toCollection(LinkedList::new));

    long result = scratchCardIds.size() + scratchCardIdToMatchingCount.size();
    while (!scratchCardIds.isEmpty()) {
      int scratchCardId = scratchCardIds.poll();
      int matchingCount = scratchCardIdToMatchingCount.get(scratchCardId);
      for (int scid = scratchCardId + 1; scid <= scratchCardId + matchingCount; scid++) {
        result++;
        scratchCardIds.add(scid);
      }
    }
    return result;
  }

}

