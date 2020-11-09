package org.dmg.dreamcaster.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;

@AllArgsConstructor
public class Formula0 implements Formula {
    @Getter
    private final String name;

    private final int value;

    @Override
    public Integer apply(int[] ints) {
        return value;
    }
}
