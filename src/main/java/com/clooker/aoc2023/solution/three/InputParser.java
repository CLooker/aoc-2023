package com.clooker.aoc2023.solution.three;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class InputParser {

  private InputParser() {}

  public static List<GearRatio> gearRatios(List<String> input) {
    List<PartNumber> partNumbers = partNumbers(input);
    List<GearRatio> gearRatios = new ArrayList<>();

    for (int c = 0; c < input.size(); c++) {
      String inputLine = input.get(c);
      for (int r = 0; r < inputLine.length(); r++) {
        char ch = inputLine.charAt(r);
        if (ch == '*') {
          PartNumber pn0 = null;
          for (int cCurr : List.of(c - 1, c, c + 1)) {
            for (PartNumber partNumber : partNumbers) {
              CellPosition cellPosition = partNumber.cellPosition();
              if (cellPosition.c() == cCurr) {
                int pnrMax = cellPosition.r() + String.valueOf(partNumber.value()).length() - 1;
                for (int pnr = cellPosition.r(); pnr <= pnrMax; pnr++) {
                  if (Math.abs(pnr - r) <= 1) {
                    if (isNull(pn0)) {
                      pn0 = partNumber;
                    } else if (!partNumber.equals(pn0)){
                      GearRatio gearRatio = new GearRatio(pn0.value() * partNumber.value());
                      gearRatios.add(gearRatio);
                      break;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    return gearRatios;
  }

  public static List<PartNumber> partNumbers(List<String> input) {
    List<CellPosition> symbolCellPositions = cellPositions(
        input,
        ch -> !Character.isDigit(ch) && ch != '.'
    );
    List<PartNumber> partNumbers = new ArrayList<>();

    for (int c = 0; c < input.size(); c++) {
      String line = input.get(c);

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

        if (!pnValueStr.isEmpty() && isCellPositionAdjacent(symbolCellPositions, c, rMin - 1, rMax)) {
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

  private static List<CellPosition> cellPositions(List<String> input, Predicate<Character> filter) {
    List<CellPosition> cellPositions = new ArrayList<>();
    for (int c = 0; c < input.size(); c++) {
      String inputLine = input.get(c);
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

  private static boolean isCellPositionAdjacent(List<CellPosition> cellPositions, int c, int rMin, int rMax) {
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

}
