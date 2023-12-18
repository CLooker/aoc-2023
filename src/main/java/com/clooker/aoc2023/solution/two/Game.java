package com.clooker.aoc2023.solution.two;

import java.util.List;
import java.util.Map;

public record Game(
    long gameId,
    List<Map<String, Integer>> colorToCountList
) {}
