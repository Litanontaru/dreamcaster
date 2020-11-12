package org.dmg.dreamcaster.editor.paser;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Power {
    public static Formula SIMPLE = (e, p, m) -> (int) (e * m - p);

    private final List<Aspect> effect;
    private final List<Aspect> payment;
    private final Formula formula;

    public double multiplier() {
        return effect.stream().flatMap(Aspect::multiplier).findFirst().orElse(1.0);
    }

    public int rate() {
        return formula.calculate(
                effect.stream().mapToInt(Aspect::rate).sum(),
                payment.stream().mapToInt(Aspect::rate).sum(),
                multiplier()
        );
    }
}