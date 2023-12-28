package com.clooker.aoc2023.solution.ten;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.clooker.aoc2023.solution.Solution;
import com.clooker.aoc2023.solution.ten.PipeLayout.Position;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class TenTwoSolution extends Solution<Long> {

  @Override
  protected Long run(Input input) {
    PipeLayout pipeLayout = parsePipeLayout(input);
    List<List<Character>> grid = pipeLayout.grid();
    Position startingPosition = pipeLayout.startingPosition();

    Queue<Position> positions = new LinkedList<>();
    positions.add(startingPosition);

    Set<Position> loop = new HashSet<>();
    loop.add(startingPosition);

    Set<Character> startingPositionChars = new HashSet<>();
    startingPositionChars.add('|');
    startingPositionChars.add('-');
    startingPositionChars.add('J');
    startingPositionChars.add('L');
    startingPositionChars.add('7');
    startingPositionChars.add('F');

    while (!positions.isEmpty()) {
      Position position = positions.remove();
      int r = position.r();
      int c = position.c();
      char ch = pipeLayout.grid().get(position.r()).get(position.c());

      Position northPosition = new Position(r - 1, c);
      Position southPosition = new Position(r + 1, c);
      Position westPosition = new Position(r, c - 1);
      Position eastPosition = new Position(r, c + 1);

      if (r > 0
          && "S|JL".contains(String.valueOf(ch))
          && "|7F".contains(String.valueOf(grid.get(r - 1).get(c)))
          && !loop.contains(northPosition)
      ) {
        loop.add(northPosition);
        positions.add(northPosition);
        if (ch == 'S') {
          Set<Character> startingPositionCharsV2 = new HashSet<>();
          startingPositionCharsV2.add('|');
          startingPositionCharsV2.add('J');
          startingPositionCharsV2.add('L');
          startingPositionChars.retainAll(startingPositionCharsV2);
        }
      }

      if (r < grid.size() - 1
          && "S|7F".contains(String.valueOf(ch))
          && "|JL".contains(String.valueOf(grid.get(r + 1).get(c)))
          && !loop.contains(southPosition)
      ) {
        loop.add(southPosition);
        positions.add(southPosition);
        if (ch == 'S') {
          Set<Character> startingPositionCharsV2 = new HashSet<>();
          startingPositionCharsV2.add('|');
          startingPositionCharsV2.add('7');
          startingPositionCharsV2.add('F');
          startingPositionChars.retainAll(startingPositionCharsV2);
        }
      }

      if (c > 0
          && "S-J7".contains(String.valueOf(ch))
          && "-LF".contains(String.valueOf(grid.get(r).get(c - 1)))
          && !loop.contains(westPosition)
      ) {
        loop.add(westPosition);
        positions.add(westPosition);
        if (ch == 'S') {
          Set<Character> startingPositionCharsV2 = new HashSet<>();
          startingPositionCharsV2.add('-');
          startingPositionCharsV2.add('J');
          startingPositionCharsV2.add('7');
          startingPositionChars.retainAll(startingPositionCharsV2);
        }
      }

      if (c < grid.get(r).size() - 1
          && "S-LF".contains(String.valueOf(ch))
          && "-J7".contains(String.valueOf(grid.get(r).get(c + 1)))
          && !loop.contains(eastPosition)
      ) {
        loop.add(eastPosition);
        positions.add(eastPosition);
        if (ch == 'S') {
          Set<Character> startingPositionCharsV2 = new HashSet<>();
          startingPositionCharsV2.add('-');
          startingPositionCharsV2.add('L');
          startingPositionCharsV2.add('F');
          startingPositionChars.retainAll(startingPositionCharsV2);
        }
      }
    }

    if (startingPositionChars.size() != 1) {
      throw new IllegalStateException();
    }

    grid.get(startingPosition.r()).set(startingPosition.c(), startingPositionChars.stream().toList().getFirst());

    for (int r = 0; r < grid.size(); r++) {
      for (int c = 0; c < grid.get(r).size(); c++) {
        Position position = new Position(r, c);
        if (!loop.contains(position)) {
          grid.get(r).set(c, '.');
        }
      }
    }

    Set<Position> nonLoop = new HashSet<>();
    for (int r = 0; r < grid.size(); r++) {
      boolean isWithin = false;
      Boolean isNorth = null;
      for (int c = 0; c < grid.get(r).size(); c++) {
        char ch = grid.get(r).get(c);
        if (ch == '|') {
          assert isNull(isNorth);
          isWithin = !isWithin;
        } else if (ch == '-') {
          assert nonNull(isNorth);
        } else if (ch == 'L' || ch == 'F') {
          assert isNull(isNorth);
          isNorth = ch == 'L';
        } else if (ch == '7' || ch == 'J') {
          assert nonNull(isNorth);
          if (ch != (isNorth ? 'J' : '7')) {
            isWithin = !isWithin;
          }
          isNorth = null;
        } else if (ch != '.') {
          throw new IllegalStateException();
        }
        if (!isWithin) {
          nonLoop.add(new Position(r, c));
        }
      }
    }

    Set<Position> union = new HashSet<>();
    union.addAll(loop);
    union.addAll(nonLoop);

    return (long) grid.size() * grid.getFirst().size() - union.size();
  }

  private PipeLayout parsePipeLayout(Input input) {
    Position startingPosition = null;
    List<List<Character>> grid = new ArrayList<>();
    for (int r = 0; r < input.lines().size(); r++) {
      List<Character> tiles = new ArrayList<>();
      String inputLine = input.lines().get(r);
      for (int c = 0; c < inputLine.length(); c++) {
        char ch = inputLine.charAt(c);
        if (ch == 'S') {
          startingPosition = new Position(r, c);
        }
        tiles.add(ch);
      }
      grid.add(tiles);
    }
    return new PipeLayout(startingPosition, grid);
  }

}

