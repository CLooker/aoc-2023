package com.clooker.aoc2023.solution.eight;

import static com.clooker.aoc2023.solution.eight.Network.Direction.LEFT;

import com.clooker.aoc2023.solution.Solution;
import com.clooker.aoc2023.solution.eight.Network.Direction;
import com.clooker.aoc2023.solution.eight.Network.Node;
import java.util.List;
import java.util.Map;

public class EightTwoSolution extends Solution<Long> {

  @Override
  protected Long run(Input input) {
    Network network = Network.parse(input);

    List<Long> stepsList = network
        .nodeIndex()
        .values()
        .stream()
        .filter(node -> node.nodeId().endsWith("A"))
        .map(node -> computeSteps(network, node))
        .toList();

    return lcm(stepsList);
  }

  private long computeSteps(Network network, Node node) {
    List<Direction> directions = network.directions();
    Map<String, Node> nodeIndex =  network.nodeIndex();

    long steps = 0;
    Node n = node;
    for (int i = 0; true; i = (i + 1) % directions.size()) {
      if (n.nodeId().endsWith("Z")) {
        break;
      }
      Direction direction = directions.get(i);
      String edgeNodeId = direction == LEFT ? n.leftNodeId() : n.rightNodeId();
      n = nodeIndex.get(edgeNodeId);
      steps++;
    }
    return steps;
  }

  private long lcm(List<Long> ns) {
    long lcm = ns.getFirst();
    for (int i = 1; i < ns.size(); i++) {
      long n = ns.get(i);
      if (lcm == 0 || n == 0) {
        break;
      }
      long absN1 = Math.abs(lcm);
      long absN2 = Math.abs(n);
      long maxAbsN = Math.max(absN1, absN2);
      long minAbsN = Math.min(absN1, absN2);
      long lcmV2 = maxAbsN;
      while (lcmV2 % minAbsN != 0) {
        lcmV2 += maxAbsN;
      }
      lcm = lcmV2;
    }
    return lcm;
  }

}

