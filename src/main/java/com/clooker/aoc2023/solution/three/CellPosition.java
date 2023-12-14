package com.clooker.aoc2023.solution.three;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public record CellPosition(
    int c,
    int r
) {

  public static boolean isAdjacent(List<CellPosition> cellPositions, int c, int rMin, int rMax) {
    for (int cCurr : List.of(c - 1, c, c + 1)) {
      for (int rCurr = rMin; rCurr <= rMax; rCurr++) {
        CellPosition cellPosition = new CellPosition(cCurr, rCurr);
        if (cellPositions.contains(cellPosition)) {
          return true;
        }
      }
    }
    return false;
  }

  public static List<CellPosition> parseAll(List<String> inputLines, Predicate<Character> filter) {
    List<CellPosition> cellPositions = new ArrayList<>();
    for (int c = 0; c < inputLines.size(); c++) {
      String inputLine = inputLines.get(c);
      for (int r = 0; r < inputLine.length(); r++) {
        char ch = inputLine.charAt(r);
        if (filter.test(ch)) {
          cellPositions.add(
              new CellPosition(c, r)
          );
        }
      }
    }
    return cellPositions;
  }

}
