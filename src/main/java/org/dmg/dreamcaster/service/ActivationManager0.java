package org.dmg.dreamcaster.service;


import org.dmg.dreamcaster.model.Activation;

public class ActivationManager0 extends AbstractActivationManager {
    private final Integer rate;

    public ActivationManager0(String key, String description, Integer rate) {
        super(key, description);
        this.rate = rate;
    }

    @Override
    public void rate(Activation activation) {
        activation.setRate(rate);
    }
}
