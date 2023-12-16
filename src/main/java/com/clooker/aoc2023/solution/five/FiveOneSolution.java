package com.clooker.aoc2023.solution.five;

import com.clooker.aoc2023.solution.Solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FiveOneSolution extends Solution<Long> {

  @Override
  protected Long run(List<String> inputLines) {
    List<Long> seeds = parseSeeds(inputLines);
    List<String> maps = parseMaps(inputLines);
    return computeLocations(seeds, maps)
        .stream()
        .min(Long::compare)
        .orElseThrow();
  }

  private List<Long> parseSeeds(List<String> inputLines) {
    return new ArrayList<>(
        Arrays
            .stream(
                inputLines
                    .getFirst()
                    .replace("seeds: ", "")
                    .split(" ")
            )
            .map(Long::parseLong)
            .toList()
    );
  }

  private List<String> parseMaps(List<String> inputLines) {
    String input = String.join(
        "\n",
        inputLines.subList(2, inputLines.size())
    );
    return Arrays
        .stream(input.split("\\n\\n"))
        .toList();
  }

  private List<Long> computeLocations(List<Long> seeds, List<String> maps) {
    List<Long> destinations = new ArrayList<>(seeds);

    maps.forEach(map -> {
      List<List<Long>> rangeGroups = Arrays
          .stream(map.split("\\n"))
          .skip(1)
          .map(mapLine ->
              Arrays
                  .stream(mapLine.split(" "))
                  .map(Long::parseLong)
                  .toList()
          )
          .toList();

      for (int i = 0; i < destinations.size(); i++) {
        long source = destinations.get(i);
        for (List<Long> rangeGroup : rangeGroups) {
          long destinationRangeStart = rangeGroup.get(0);
          long sourceRangeStart = rangeGroup.get(1);
          long rangeLength = rangeGroup.get(2);
          if (sourceRangeStart <= source && source < (sourceRangeStart + rangeLength)) {
            long destination = source - sourceRangeStart + destinationRangeStart;
            destinations.set(i, destination);
            break;
          }
        }
      }
    });

    return destinations;
  }

}

