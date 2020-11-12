package org.dmg.dreamcaster.editor.paser;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class OptionElement implements Element {
    private final Map<String, Integer> options;

    @Override
    public Integer map(Object key) {
        return options.getOrDefault(key.toString(), 0);
    }
}
