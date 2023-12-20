package com.clooker.aoc2023.solution.seven;

public record Hand(
    String hand,
    HandType handType,
    long bid
)  {

  enum HandType {
    FIVE_OF_A_KIND,
    FOUR_OF_A_KIND,
    FULL_HOUSE,
    THREE_OF_A_KIND,
    TWO_PAIR,
    ONE_PAIR,
    HIGH_CARD
  }

}
