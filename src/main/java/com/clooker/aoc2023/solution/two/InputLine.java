package com.clooker.aoc2023.solution.two;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record InputLine(
    Long id,
    List<Map<String, Integer>> countToColorList
) {
  public static InputLine parse(String inputLine) {
    String[] splitLine = inputLine.split(":");
    Long id = Long.parseLong(splitLine[0].replace("Game ", ""));
    String[] countAndColorGroupsArr = splitLine[1].split(";");
    List<Map<String, Integer>> colorToCountList = new ArrayList<>();
    for (String countAndColorGroupsStr : countAndColorGroupsArr) {
      Map<String, Integer> colorToCount = new HashMap<>();
      String[] countAndColorGroups = countAndColorGroupsStr.split(",");
      for (String countAndColorGroup : countAndColorGroups) {
        String[] splitCountAndColorsGroup = countAndColorGroup.trim().split(" ");
        String countStr = splitCountAndColorsGroup[0];
        String color = splitCountAndColorsGroup[1];
        int count = Integer.parseInt(countStr);
        colorToCount.put(color, count);
      }
      colorToCountList.add(colorToCount);
    }
    return new InputLine(id, colorToCountList);
  }
}
