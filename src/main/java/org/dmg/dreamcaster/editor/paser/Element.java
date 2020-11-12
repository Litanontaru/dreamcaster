package org.dmg.dreamcaster.editor.paser;

import org.dmg.dreamcaster.editor.yaml.YElement;

import java.util.Map;

public interface Element {
    static Element of(YElement element) {
        Map<String, Number> options = element.getOptions();
        return options != null && !options.isEmpty()
                ? of(options)
                : new BasicElement(element);
    }

    static Element of(Map<String, Number> options) {
        return options.keySet().stream().allMatch(k -> k.matches("(\\d)+-(\\d)+"))
                ? new RangeElement(options)
                : new OptionElement(options);
    }

    default Integer map(Object key) {
        return 0;
    }

    default Double multiplier(Object key) {
        return null;
    }
}