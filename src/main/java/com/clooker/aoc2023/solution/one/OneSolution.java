package com.clooker.aoc2023.solution.one;

import static com.clooker.aoc2023.solution.one.CalibrationEncoding.DIGITS_AND_LETTERS;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.clooker.aoc2023.solution.Solution;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class OneSolution extends Solution<Integer> {

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

  private final CalibrationEncoding calibrationEncoding;

  @Override
  protected Integer run(List<String> inputLines) {
    return inputLines
        .stream()
        .map(this::parseCalibration)
        .mapToInt(Integer::intValue)
        .sum();
  }

  private int parseCalibration(String inputLine) {
    if (calibrationEncoding.equals(DIGITS_AND_LETTERS)) {
      for (Entry<String, String> entry : DIGIT_NAME_TO_REPLACEMENT.entrySet()) {
        String digitName = entry.getKey();
        String replacement = entry.getValue();
        inputLine = inputLine.replace(digitName, replacement);
      }
    }

    Character lDigitCh = null;
    Character rDigitCh = null;
    for (int i = 0, j = inputLine.length() - 1; i < inputLine.length() && j >= 0; i++, j--) {
      if (nonNull(lDigitCh) && nonNull(rDigitCh)) {
        break;
      }
      if (isNull(lDigitCh)) {
        char lCh = inputLine.charAt(i);
        if (Character.isDigit(lCh)) {
          lDigitCh = lCh;
        }
      }
      if (isNull(rDigitCh)) {
        char rCh = inputLine.charAt(j);
        if (Character.isDigit(rCh)) {
          rDigitCh = rCh;
        }
      }
    }

    if (isNull(lDigitCh) || isNull(rDigitCh)) {
      String missingDigitLocation = isNull(lDigitCh) ? "left" : "right";
      throw new IllegalStateException(
          STR."failed finding \{missingDigitLocation} digit in inputLine \{inputLine}"
      );
    }

    return Integer.parseInt(
        STR."\{lDigitCh}\{rDigitCh}"
    );
  }

}
