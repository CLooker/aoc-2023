package com.clooker.aoc2023.solution;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.SneakyThrows;

public abstract class Solution<T> {

  public final T run(String inputLocation) {
    return run(Input.load(inputLocation));
  }

  protected abstract T run(Input input);

  public record Input(String raw, List<String> lines) {
    @SneakyThrows
    public static Input load(String inputLocation) {
      Path inputPath = Stream
          .of(
              inputLocation,
              Optional
                  .ofNullable(Input.class.getClassLoader().getResource(inputLocation))
                  .map(URL::getFile)
                  .orElse(null)
          )
          .filter(Objects::nonNull)
          .map(Path::of)
          .filter(Files::exists)
          .findAny()
          .orElseThrow(() ->
              new IllegalArgumentException(
                  STR."failed finding file at input location \{inputLocation}"
              )
          );

      String raw = Files.readString(inputPath);
      List<String> lines = Files.readAllLines(inputPath);

      return new Input(raw, lines);
    }
  }

}
