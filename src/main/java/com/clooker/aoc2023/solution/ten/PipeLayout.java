package com.clooker.aoc2023.solution.ten;

import java.util.List;

public record PipeLayout(
    Position startingPosition,
    List<List<Character>> grid
) {

  record Position(int r, int c) {}

}
