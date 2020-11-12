package org.dmg.dreamcaster.editor.paser;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public class Aspect {
    private final String name;
    private final Integer base;
    private final Map<String, Integer> params;
    private final Double multiplier;

    public int rate() {
        return params.values().stream().mapToInt(i -> i).sum() + base;
    }

    public Stream<Double> multiplier() {
        return multiplier == null ? Stream.empty() : Stream.of(multiplier);
    }
}