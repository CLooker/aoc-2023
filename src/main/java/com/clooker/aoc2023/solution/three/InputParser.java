package com.clooker.aoc2023.solution.three;

import static java.util.Objects.isNull;

import com.clooker.aoc2023.solution.Solution.Input;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class InputParser {

  private InputParser() {}

  static List<GearRatio> parseGearRatios(Input input) {
    List<String> inputLines = input.lines();
    List<PartNumber> partNumbers = parsePartNumbers(input);
    List<GearRatio> gearRatios = new ArrayList<>();

    for (int c = 0; c < inputLines.size(); c++) {
      String inputLine = inputLines.get(c);
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

  static List<CellPosition> parseCellPositions(Input input, Predicate<Character> filter) {
    List<String> inputLines = input.lines();
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

  static List<PartNumber> parsePartNumbers(Input input) {
    List<String> inputLines = input.lines();
    List<CellPosition> symbolCellPositions = InputParser.parseCellPositions(
        input,
        ch -> !Character.isDigit(ch) && ch != '.'
    );

    List<PartNumber> partNumbers = new ArrayList<>();

    for (int c = 0; c < inputLines.size(); c++) {
      String inputLine = inputLines.get(c);

      for (int rMin = 0; rMin < inputLine.length();) {
        int rMax = rMin;
        StringBuilder pnValueStr = new StringBuilder();

        while (rMax < inputLine.length()) {
          char ch = inputLine.charAt(rMax);
          if (Character.isDigit(ch)) {
            pnValueStr.append(ch);
            rMax++;
          } else {
            break;
          }
        }

        if (!pnValueStr.isEmpty() && hasAdjacentCellPosition(symbolCellPositions, c, rMin - 1, rMax)) {
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

  private static boolean hasAdjacentCellPosition(List<CellPosition> cellPositions, int c, int rMin, int rMax) {
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
