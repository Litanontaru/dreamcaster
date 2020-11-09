package org.dmg.dreamcaster.model;

import static org.dmg.dreamcaster.ContextAccess.CONTEXT;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SimplePower {
    private Effect effect;
    private List<Activation> activation;

    public void setEffect(String name) {
        effect = CONTEXT.createEffect(name);
    }

    public void addActivation(String name) {
        activation.add(CONTEXT.createActivation(name));
    }

    public void removeActivation(int number) {
        activation.remove(number);
    }
}