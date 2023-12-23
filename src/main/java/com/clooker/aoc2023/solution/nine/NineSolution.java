package com.clooker.aoc2023.solution.nine;

import com.clooker.aoc2023.solution.Solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class NineSolution extends Solution<Long> {

  enum ExtrapolationDirection{
    FORWARD,
    BACKWARD
  }

  private final ExtrapolationDirection extrapolationDirection;

  @Override
  protected Long run(Input input) {
    return parseHistories(input)
        .stream()
        .map(this::extrapolateHistory)
        .mapToLong(Long::longValue)
        .sum();
  }

  private List<List<Long>> parseHistories(Input input) {
    return input
        .lines()
        .stream()
        .map(inputLine -> Arrays.stream(inputLine.trim().split(" ")).map(Long::valueOf).toList())
        .toList();
  }

  private long extrapolateHistory(List<Long> history) {
    List<List<Long>> histories = new ArrayList<>();
    histories.add(new ArrayList<>(history));
    while (histories.getFirst().size() != history.size() + 1) {
      List<Long> prevHistory = histories.getLast();
      List<Long> nextHistory = new ArrayList<>();
      for (int i = 1; i < prevHistory.size(); i++) {
        nextHistory.add(prevHistory.get(i) - prevHistory.get(i - 1));
      }
      histories.add(nextHistory);
      Set<Long> uniqueHistory = new HashSet<>(nextHistory);
      if (uniqueHistory.size() == 1) {
        List<Long> zeroes = new ArrayList<>();
        for (int i = 0; i < nextHistory.size(); i++) {
          zeroes.add(0L);
        }
        histories.add(zeroes);
        switch (extrapolationDirection) {
          case FORWARD -> {
            for (int i = histories.size() - 1; i > 0; i--) {
              List<Long> history2 = histories.get(i);
              List<Long> history1 = histories.get(i - 1);
              history1.add(history1.getLast() + history2.getLast());
            }
          }
          case BACKWARD -> {
            histories.replaceAll(List::reversed);
            for (int i = histories.size() - 1; i > 0; i--) {
              List<Long> history2 = histories.get(i);
              List<Long> history1 = histories.get(i - 1);
              history1.add(history1.getLast() - history2.getLast());
            }
          }
          default -> throw new UnsupportedOperationException();
        }
      }
    }
    return histories.getFirst().getLast();
  }

}

