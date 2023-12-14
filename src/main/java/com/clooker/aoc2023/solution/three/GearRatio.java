package com.clooker.aoc2023.solution.three;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;

public record GearRatio(
    int value
) {
  public static List<GearRatio> parseAll(List<String> inputLines) {
    List<PartNumber> partNumbers = PartNumber.parseAll(inputLines);
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
}
