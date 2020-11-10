package org.dmg.dreamcaster.service;

import lombok.RequiredArgsConstructor;
import org.dmg.dreamcaster.model.Activation;
import org.dmg.dreamcaster.model.Power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PowerManager {
    private final EffectLocator effectLocator;
    private final ActivationLocator activationLocator;

    public void rate(Power power) {
        effectLocator.rate(power.getEffect());
        Integer rate = (int) (power.getEffect().getRate() * power.getEffect().getMultiplier());
        for (Activation activation : power.getActivations()) {
            activationLocator.rate(activation);
            rate -= activation.getRate();
        }
        power.setRate(rate);
    }
}
