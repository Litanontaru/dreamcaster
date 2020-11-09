package org.dmg.dreamcaster.model;

import java.util.function.Function;

public interface Formula extends Function<int[], Integer> {
    String getName();
}
