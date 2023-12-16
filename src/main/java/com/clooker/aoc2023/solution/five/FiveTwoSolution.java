package com.clooker.aoc2023.solution.five;

import com.clooker.aoc2023.solution.Solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FiveTwoSolution extends Solution<Long> {

  @Override
  protected Long run(List<String> inputLines) {
    List<Range> seedRanges = parseSeedRanges(inputLines);
    List<String> maps = parseMaps(inputLines);
    List<Range> locationRanges = computeLocationRanges(seedRanges, maps);
    return computeMinLocation(locationRanges);
  }

  private List<Range> parseSeedRanges(List<String> inputLines) {
    String seedRangesLine = inputLines.getFirst().replace("seeds: ", "");
    String[] seedRangeStrs = seedRangesLine.split(" ");
    List<Range> ranges = new ArrayList<>();
    for (int i = 0; i < seedRangeStrs.length; i += 2) {
      long start = Long.parseLong(seedRangeStrs[i]);
      long range = Long.parseLong(seedRangeStrs[i + 1]);
      long end = start + range;
      ranges.add(new Range(start, end));
    }
    return ranges;
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

  private List<Range> computeLocationRanges(List<Range> seedRanges, List<String> maps) {
    List<Range> locationRanges = new ArrayList<>(seedRanges);

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

      List<Range> locationRangesV2 = new ArrayList<>();

      while (!locationRanges.isEmpty()) {
        Range locationRange = locationRanges.removeLast();
        long start = locationRange.start();
        long end = locationRange.end();
        locationRangesV2.add(locationRange);
        for (List<Long> rangeGroup : rangeGroups) {
          long destinationRangeStart = rangeGroup.get(0);
          long sourceRangeStart = rangeGroup.get(1);
          long rangeLength = rangeGroup.get(2);
          long overlappingRangeStart = Math.max(start, sourceRangeStart);
          long overlappingRangeEnd = Math.min(end, sourceRangeStart + rangeLength);
          if (overlappingRangeStart < overlappingRangeEnd) {
            locationRangesV2.removeLast();
            locationRangesV2.add(
                new Range(
                    overlappingRangeStart - sourceRangeStart + destinationRangeStart,
                    overlappingRangeEnd - sourceRangeStart + destinationRangeStart
                )
            );
            if (overlappingRangeStart > start) {
              locationRanges.add(new Range(start, overlappingRangeStart));
            }
            if (end > overlappingRangeEnd) {
              locationRanges.add(new Range(overlappingRangeEnd, end));
            }
            break;
          }
        }
      }

      locationRanges.addAll(locationRangesV2);
    });

    return locationRanges;
  }

  private long computeMinLocation(List<Range> ranges) {
    return ranges
        .stream()
        .min((sr1, sr2) -> sr1.start() < sr2.start() ? -1 : 1)
        .orElseThrow()
        .start();
  }

  private record Range(long start, long end) {}

}

