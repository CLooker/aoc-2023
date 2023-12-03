package com.clooker.aoc2023.solution.one;

import java.util.Map;
import java.util.Map.Entry;

public class OneTwoSolution extends OneOneSolution {

  private static final Map<String, String> DIGIT_NAME_TO_REPLACEMENT = Map.of(
      "one", "o1e",
      "two", "t2o",
      "three", "t3e",
      "four", "f4r",
      "five", "f5e",
      "six", "s6x",
      "seven", "s7n",
      "eight", "e8t",
      "nine", "n9e"
  );

  @Override
  protected int extractDigits(String line) {
    for (Entry<String, String> entry : DIGIT_NAME_TO_REPLACEMENT.entrySet()) {
      String digitName = entry.getKey();
      String replacement = entry.getValue();
      line = line.replace(digitName, replacement);
    }
    return super.extractDigits(line);
  }

}
