package com.clooker.aoc2023.solution.eight;

import static com.clooker.aoc2023.solution.eight.Network.Direction.LEFT;

import com.clooker.aoc2023.solution.Solution;
import com.clooker.aoc2023.solution.eight.Network.Direction;
import com.clooker.aoc2023.solution.eight.Network.Node;
import java.util.List;
import java.util.Map;

public class EightOneSolution extends Solution<Long> {

  @Override
  protected Long run(Input input) {
    Network network = Network.parse(input);
    List<Direction> directions = network.directions();
    Map<String, Node> nodeIndex =  network.nodeIndex();

    long steps = 0;
    Network.Node node = nodeIndex.get("AAA");
    for (int i = 0; true; i = (i + 1) % directions.size()) {
      if (node.nodeId().equals("ZZZ")) {
        break;
      }
      Direction direction = directions.get(i);
      String edgeNodeId = direction == LEFT ? node.leftNodeId() : node.rightNodeId();
      node = nodeIndex.get(edgeNodeId);
      steps++;
    }
    return steps;
  }

}

