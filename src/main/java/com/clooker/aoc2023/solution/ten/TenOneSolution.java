package com.clooker.aoc2023.solution.ten;

import com.clooker.aoc2023.solution.Solution;
import com.clooker.aoc2023.solution.ten.PipeLayout.Position;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class TenOneSolution extends Solution<Long> {

  @Override
  protected Long run(Input input) {
    PipeLayout pipeLayout = parsePipeLayout(input);
    List<List<Character>> grid = pipeLayout.grid();
    Position startingPosition = pipeLayout.startingPosition();

    Queue<Position> positions = new LinkedList<>();
    positions.add(startingPosition);

    Set<Position> loop = new HashSet<>();
    loop.add(startingPosition);

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
      }

      if (r < grid.size() - 1
          && "S|7F".contains(String.valueOf(ch))
          && "|JL".contains(String.valueOf(grid.get(r + 1).get(c)))
          && !loop.contains(southPosition)
      ) {
        loop.add(southPosition);
        positions.add(southPosition);
      }

      if (c > 0
          && "S-J7".contains(String.valueOf(ch))
          && "-LF".contains(String.valueOf(grid.get(r).get(c - 1)))
          && !loop.contains(westPosition)
      ) {
        loop.add(westPosition);
        positions.add(westPosition);
      }

      if (c < grid.get(r).size() - 1
          && "S-LF".contains(String.valueOf(ch))
          && "-J7".contains(String.valueOf(grid.get(r).get(c + 1)))
          && !loop.contains(eastPosition)
      ) {
        loop.add(eastPosition);
        positions.add(eastPosition);
      }
    }

    return (long) (loop.size() / 2);
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

