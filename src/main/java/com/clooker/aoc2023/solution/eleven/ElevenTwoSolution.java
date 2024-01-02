package com.clooker.aoc2023.solution.eleven;

import com.clooker.aoc2023.solution.Solution;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ElevenTwoSolution extends Solution<Long> {

  @Override
  protected Long run(Input input) {
    Set<Integer> emptyRows = computeEmptyRows(input.lines());
    Set<Integer> emptyCols = computeEmptyCols(input.lines());
    List<Position> galaxyPositions = computeGalaxyPositions(input.lines());
    return sumGalaxyDistances(emptyRows, emptyCols, galaxyPositions);
  }

  private Set<Integer> computeEmptyRows(List<String> lines) {
    return IntStream
        .range(0, lines.size())
        .boxed()
        .filter(r -> !lines.get(r).contains("#"))
        .collect(Collectors.toSet());
  }

  private Set<Integer> computeEmptyCols(List<String> lines) {
    return IntStream
        .range(0, lines.getFirst().length())
        .boxed()
        .filter(c -> lines.stream().allMatch(line -> line.charAt(c) == '.'))
        .collect(Collectors.toSet());
  }

  private List<Position> computeGalaxyPositions(List<String> lines) {
    return IntStream
        .range(0, lines.size())
        .boxed()
        .flatMap(r ->
            IntStream
                .range(0, lines.get(r).length())
                .boxed()
                .filter(c -> lines.get(r).charAt(c) == '#')
                .map(c -> new Position(r, c))
        )
        .toList();
  }

  private long sumGalaxyDistances(Set<Integer> emptyRows, Set<Integer> emptyCols, List<Position> galaxyPositions) {
    int spaceExpansionScale = 1_000_000;
    long total = 0;
    for (int i = 0; i < galaxyPositions.size(); i++) {
      Position gp1 = galaxyPositions.get(i);
      for (int j = i + 1; j < galaxyPositions.size(); j++) {
        Position gp2 = galaxyPositions.get(j);
        int minR = Math.min(gp1.r(), gp2.r());
        int maxR = Math.max(gp1.r(), gp2.r());
        for (int r : IntStream.range(minR, maxR).boxed().toList()) {
          total += (emptyRows.contains(r) ? spaceExpansionScale : 1);
        }
        int minC = Math.min(gp1.c(), gp2.c());
        int maxC = Math.max(gp1.c(), gp2.c());
        for (int c : IntStream.range(minC, maxC).boxed().toList()) {
          total += (emptyCols.contains(c) ? spaceExpansionScale : 1);
        }
      }
    }
    return total;
  }

  private record Position(int r, int c) {}

}

