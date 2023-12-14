package com.clooker.aoc2023.solution.three;

import java.util.ArrayList;
import java.util.List;

public record PartNumber(
    int value,
    CellPosition cellPosition
) {
  public static List<PartNumber> parseAll(List<String> inputLines) {
    List<CellPosition> symbolCellPositions = CellPosition.parseAll(
        inputLines,
        ch -> !Character.isDigit(ch) && ch != '.'
    );

    List<PartNumber> partNumbers = new ArrayList<>();

    for (int c = 0; c < inputLines.size(); c++) {
      String line = inputLines.get(c);

      for (int rMin = 0; rMin < line.length();) {
        int rMax = rMin;
        StringBuilder pnValueStr = new StringBuilder();

        while (rMax < line.length()) {
          char ch = line.charAt(rMax);
          if (Character.isDigit(ch)) {
            pnValueStr.append(ch);
            rMax++;
          } else {
            break;
          }
        }

        if (!pnValueStr.isEmpty() && CellPosition.isAdjacent(symbolCellPositions, c, rMin - 1, rMax)) {
          int partNumberValue = Integer.parseInt(
              pnValueStr.toString()
          );
          PartNumber partNumber = new PartNumber(partNumberValue, new CellPosition(c, rMin));
          partNumbers.add(partNumber);
        }

        rMin = rMax + 1;
      }
    }

    return partNumbers;
  }
}
