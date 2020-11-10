package org.dmg.dreamcaster.service;

import org.dmg.dreamcaster.model.Activation;

import java.util.HashMap;

public class ActivationManagerX extends AbstractActivationManager {
    private final Double multiplier;
    private final Integer addition;

    public ActivationManagerX(String key, String description, Double multiplier, Integer addition) {
        super(key, description);
        this.multiplier = multiplier;
        this.addition = addition;
    }

    @Override
    public Activation create() {
        Activation activation = super.create();
        activation.setParams(new HashMap<>());
        activation.getParams().put("X", 1);
        return activation;
    }

    @Override
    public void rate(Activation activation) {
        Integer x = (Integer) activation.getParams().get("X");
        activation.setRate((int) (x * multiplier + addition));
    }
}
