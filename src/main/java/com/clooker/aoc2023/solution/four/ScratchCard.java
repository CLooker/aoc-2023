package com.clooker.aoc2023.solution.four;

import java.util.List;

public record ScratchCard(
    int scratchCardId,
    List<Integer> winningNumbers,
    List<Integer> numbers
) {}
