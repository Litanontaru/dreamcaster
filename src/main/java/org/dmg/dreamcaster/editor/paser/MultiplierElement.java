package org.dmg.dreamcaster.editor.paser;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class MultiplierElement implements Element {
    private final Map<String, Double> multipliers;

    @Override
    public Double multiplier(Object key) {
        return multipliers.getOrDefault(key.toString(), 0.0);
    }
}
