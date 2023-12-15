package com.clooker.aoc2023.solution.four;

import java.util.ArrayList;
import java.util.List;

public record ScratchCard(
    int scratchCardId,
    List<Integer> winningNumbers,
    List<Integer> numbers
) {

  public static List<ScratchCard> parseAll(List<String> inputLines) {
    return inputLines
        .stream()
        .map(inputLine -> {
          String[] splitInputLine = inputLine.split(":");
          int scratchCardId = Integer.parseInt(
              splitInputLine[0]
                  .replace(" ", "")
                  .replace("Card", "")
          );
          List<List<Integer>> numbersList = new ArrayList<>();
          for (String numbersStr : splitInputLine[1].split("\\|")) {
            List<Integer> numbers = new ArrayList<>();
            for (String numberStr : numbersStr.trim().split(" ")) {
              if (!numberStr.isEmpty()) {
                numbers.add(Integer.parseInt(numberStr));
              }
            }
            numbersList.add(numbers);
          }
          if (numbersList.size() != 2) {
            throw new IllegalStateException(STR."expected 2 lists of numbers but found \{numbersList.size()}");
          }
          return new ScratchCard(
              scratchCardId,
              numbersList.get(0),
              numbersList.get(1)
          );
        })
        .toList();
  }

}
