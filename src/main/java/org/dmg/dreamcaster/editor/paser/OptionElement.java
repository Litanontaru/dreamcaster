package org.dmg.dreamcaster.editor.paser;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class OptionElement implements Element {
    private final Map<String, Number> options;

    @Override
    public Integer map(Object key) {
        return options.getOrDefault(key.toString(), 0).intValue();
    }

    @Override
    public Double multiplier(Object key) {
        Number number = options.get(key.toString());
        if (number instanceof Double) {
            return number.doubleValue();
        } else {
            return null;
        }
    }
}
