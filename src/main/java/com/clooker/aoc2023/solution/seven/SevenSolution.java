package com.clooker.aoc2023.solution.seven;

import com.clooker.aoc2023.solution.Solution;
import com.clooker.aoc2023.solution.seven.Hand.HandType;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class SevenSolution extends Solution<Long> {

  private static final Character WILDCARD = 'J';
  private static final Map<StrengthType, List<Character>> STRENGTH_TYPE_TO_CARDS = Map.of(
      StrengthType.STANDARD, List.of('A', 'K', 'Q', WILDCARD, 'T', '9', '8', '7', '6', '5', '4', '3', '2'),
      StrengthType.WILDCARD, List.of('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', WILDCARD)
  );

  private final StrengthType strengthType;

  @Override
  protected Long run(Input input) {
    return sumHands(sortHands(parseHands(input)));
  }

  private List<Hand> parseHands(Input input) {
    return input
        .lines()
        .stream()
        .map(inputLine -> {
          String[] handAndBidStrs = inputLine.trim().split(" ");
          String hand = handAndBidStrs[0];
          HandType handType = parseHandType(hand);
          long bid = Long.parseLong(handAndBidStrs[1]);
          return new Hand(hand, handType, bid);
        })
        .toList();
  }

  private List<Hand> sortHands(List<Hand> hands) {
    return hands
        .stream()
        .sorted((h1, h2) -> {
          if (!h1.handType().equals(h2.handType())) {
            return Integer.compare(h1.handType().ordinal(), h2.handType().ordinal());
          } else {
            List<Character> cards = STRENGTH_TYPE_TO_CARDS.get(strengthType);
            for (int i = 0; i < h1.hand().length(); i++) {
              if (h1.hand().charAt(i) != h2.hand().charAt(i)) {
                int idx = cards.indexOf(h1.hand().charAt(i));
                int otherIdx = cards.indexOf(h2.hand().charAt(i));
                return Integer.compare(idx, otherIdx);
              }
            }
            throw new IllegalArgumentException();
          }
        })
        .toList();
  }

  private HandType parseHandType(String hand) {
    Map<Character, Long> cardToFreq = new HashMap<>();
    for (int i = 0; i < hand.length(); i++) {
      char card = hand.charAt(i);
      cardToFreq.put(
          card,
          cardToFreq.containsKey(card) ? cardToFreq.get(card) + 1 : 1
      );
    }
    if (
        strengthType == StrengthType.WILDCARD
            && cardToFreq.containsKey(WILDCARD)
            && cardToFreq.get(WILDCARD) != hand.length()
    ) {
      Entry<Character, Long> cardToMaxFreq = cardToFreq
          .entrySet()
          .stream()
          .filter(entry -> entry.getKey() != WILDCARD)
          .sorted(Comparator.comparingLong(Entry::getValue))
          .toList()
          .getLast();
      char card = cardToMaxFreq.getKey();
      long maxFreq = cardToMaxFreq.getValue();
      cardToFreq.put(card, maxFreq + cardToFreq.get(WILDCARD));
      cardToFreq.put(WILDCARD, 0L);
    }
    Set<Long> freqs = new HashSet<>(cardToFreq.values());
    if (freqs.contains(5L)) {
      return HandType.FIVE_OF_A_KIND;
    } else if (freqs.contains(4L)) {
      return HandType.FOUR_OF_A_KIND;
    } else if (freqs.contains(3L) && freqs.contains(2L)) {
      return HandType.FULL_HOUSE;
    } else if (freqs.contains(3L)) {
      return HandType.THREE_OF_A_KIND;
    } else if (cardToFreq.values().stream().filter(freq -> freq == 2).count() == 2) {
      return HandType.TWO_PAIR;
    } else if (freqs.contains(2L)) {
      return HandType.ONE_PAIR;
    } else {
      return HandType.HIGH_CARD;
    }
  }

  private long sumHands(List<Hand> hands) {
    long sum = 0;
    for (int i = 0; i < hands.size(); i++) {
      int rank = hands.size() - i;
      long bid = hands.get(i).bid();
      sum += (rank * bid);
    }
    return sum;
  }

}
