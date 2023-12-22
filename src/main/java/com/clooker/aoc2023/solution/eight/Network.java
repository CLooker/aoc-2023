package com.clooker.aoc2023.solution.eight;

import static com.clooker.aoc2023.solution.eight.Network.Direction.LEFT;
import static com.clooker.aoc2023.solution.eight.Network.Direction.RIGHT;

import com.clooker.aoc2023.solution.Solution.Input;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record Network(
   List<Direction> directions,
   Map<String, Node> nodeIndex
) {

  enum Direction{
    LEFT,
    RIGHT
  }

  record Node(String nodeId, String leftNodeId, String rightNodeId) {}

  public static Network parse(Input input) {
    List<String> inputLines = input.lines();

    List<Direction> directions = Arrays
        .stream(inputLines.getFirst().split(""))
        .map(d -> d.equals("L") ? LEFT : RIGHT)
        .toList();

    Map<String, Node> nodeIndex = inputLines
        .subList(2, inputLines.size())
        .stream()
        .map(inputLine -> {
          String nodeId = inputLine.split("=")[0].trim();
          String edgeNodeIdsStr = inputLine.split("= ")[1].trim();
          String leftEdgeNodeId = edgeNodeIdsStr.split(",")[0].replace("(", "");
          String rightEdgeNodeId = edgeNodeIdsStr.split(", ")[1].replace(")", "");
          return new Node(nodeId, leftEdgeNodeId, rightEdgeNodeId);
        })
        .collect(
            Collectors.toMap(
                Node::nodeId,
                node -> node
            )
        );

    return new Network(directions, nodeIndex);
  }

}
