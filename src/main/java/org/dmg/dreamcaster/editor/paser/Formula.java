package org.dmg.dreamcaster.editor.paser;

@FunctionalInterface
public interface Formula {
    Integer calculate(int effect, int payment, double multiplier);
}
