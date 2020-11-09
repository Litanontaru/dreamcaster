package org.dmg.dreamcaster.model;

import static org.dmg.dreamcaster.ContextAccess.CONTEXT;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivationConstant implements Activation {
    private String name;

    @Override
    public int rate() {
        return CONTEXT.formula(name).apply(new int[0]);
    }
}
