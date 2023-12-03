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
    return run(
        findInput(inputLocation)
    );
  }

  protected abstract T run(List<String> input);

  @SneakyThrows
  private List<String> findInput(String inputLocation) {
    Path inputPath = Stream
        .of(
            inputLocation,
            Optional
                .ofNullable(getClass().getClassLoader().getResource(inputLocation))
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

    return Files.readAllLines(inputPath);
  }
}
