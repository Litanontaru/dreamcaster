package org.dmg.dreamcaster.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Formula1 implements Formula {
    @Getter
    private final String name;

    private final double multiplier;
    private final int addition;

    @Override
    public Integer apply(int[] ints) {
        return (int) (ints[0] * multiplier + addition);
    }
}
