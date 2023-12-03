package com.clooker.aoc2023.solution.one;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.clooker.aoc2023.solution.Solution;
import java.util.List;

public class OneOneSolution extends Solution<Integer> {

  @Override
  protected Integer run(List<String> input) {
    return input
        .stream()
        .map(this::extractDigits)
        .reduce(0, Integer::sum);
  }

  protected int extractDigits(String line) {
    Character lDigitCh = null;
    Character rDigitCh = null;
    for (int i = 0, j = line.length() - 1; i < line.length() && j >= 0; i++, j--) {
      if (nonNull(lDigitCh) && nonNull(rDigitCh)) {
        break;
      }
      if (isNull(lDigitCh)) {
        char lCh = line.charAt(i);
        if (Character.isDigit(lCh)) {
          lDigitCh = lCh;
        }
      }
      if (isNull(rDigitCh)) {
        char rCh = line.charAt(j);
        if (Character.isDigit(rCh)) {
          rDigitCh = rCh;
        }
      }
    }

    if (isNull(lDigitCh) || isNull(rDigitCh)) {
      String missingDigitLocation = isNull(lDigitCh) ? "left" : "right";
      throw new IllegalStateException(
          STR."failed finding \{missingDigitLocation} digit in line \{line}"
      );
    }

    return Integer.parseInt(
        STR."\{lDigitCh}\{rDigitCh}"
    );
  }

}
