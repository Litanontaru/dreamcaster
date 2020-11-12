package org.dmg.dreamcaster.editor.paser;

import org.dmg.dreamcaster.editor.yaml.YElement;

public class BasicElement implements Element {
    private final Double multiply;
    private final Integer base;

    BasicElement(YElement element) {
        multiply = element.getMultiply() == null ? 1.0 : element.getMultiply();
        base = element.getBase() == null ? 0 : element.getBase();
    }

    @Override
    public Integer map(Object key) {
        Integer i = (Integer) key;
        return (int) (i * multiply + base);
    }
}
