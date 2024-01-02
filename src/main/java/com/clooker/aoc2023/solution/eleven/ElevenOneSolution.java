package com.clooker.aoc2023.solution.eleven;

import com.clooker.aoc2023.solution.Solution;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ElevenOneSolution extends Solution<Long> {

  @Override
  protected Long run(Input input) {
    List<String> expandedSpace = expandSpace(input.lines());
    List<Position> galaxyPositions = computeGalaxyPositions(expandedSpace);
    List<Integer> galaxyDistances = computeGalaxyDistances(galaxyPositions);
    return galaxyDistances.stream().mapToLong(Integer::longValue).sum();
  }

  private List<String> expandSpace(List<String> space) {
    Set<Integer> expandableRs = IntStream
        .range(0, space.size())
        .boxed()
        .filter(r -> !space.get(r).contains("#"))
        .collect(Collectors.toSet());

    Set<Integer> expandableCs = IntStream
        .range(0, space.getFirst().length())
        .boxed()
        .filter(c -> space.stream().noneMatch(quantums -> quantums.charAt(c) == '#'))
        .collect(Collectors.toSet());

    List<String> exapndedSpace = new ArrayList<>();
    for (int r = 0; r < space.size(); r++) {
      String quantums = space.get(r);
      StringBuilder quantumsBuilder = new StringBuilder();
      for (int c = 0; c < space.get(r).length(); c++) {
        quantumsBuilder.append(quantums.charAt(c));
        if (expandableCs.contains(c)) {
          quantumsBuilder.append('.');
        }
      }
      exapndedSpace.add(quantumsBuilder.toString());
      if (expandableRs.contains(r)) {
        exapndedSpace.add(".".repeat(quantumsBuilder.length()));
      }
    }
    return exapndedSpace;
  }

  private List<Position> computeGalaxyPositions(List<String> space) {
    List<Position> galaxyPositions = new ArrayList<>();
    for (int r = 0; r < space.size(); r++) {
      for (int c = 0; c < space.get(r).length(); c++) {
        if (space.get(r).charAt(c) == '#') {
          galaxyPositions.add(new Position(r, c));
        }
      }
    }
    return galaxyPositions;
  }

  private List<Integer> computeGalaxyDistances(List<Position> galaxyPositions) {
    List<Integer> galaxyDistances = new ArrayList<>();
    for (int i = 0; i < galaxyPositions.size(); i++) {
      for (int j = i + 1; j < galaxyPositions.size(); j++) {
        Position gp1 = galaxyPositions.get(i);
        Position gp2 = galaxyPositions.get(j);
        int distance = Math.abs(gp1.r - gp2.r) + Math.abs(gp1.c - gp2.c);
        galaxyDistances.add(distance);
      }
    }
    return galaxyDistances;
  }

  private record Position(int r, int c) {}

}

