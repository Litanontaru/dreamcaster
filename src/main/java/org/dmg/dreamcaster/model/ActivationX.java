package org.dmg.dreamcaster.model;


import static org.dmg.dreamcaster.ContextAccess.CONTEXT;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ActivationX implements Activation {
    private final String name;
    private int x = 1;

    @Override
    public int rate() {
        return CONTEXT.formula(name).apply(new int[]{x});
    }
}