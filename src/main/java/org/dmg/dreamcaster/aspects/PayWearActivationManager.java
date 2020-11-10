package org.dmg.dreamcaster.aspects;

import org.dmg.dreamcaster.model.Activation;
import org.dmg.dreamcaster.service.ActivationManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class PayWearActivationManager implements ActivationManager {
    @Override
    public String getKey() {
        return "pay_wear";
    }

    @Override
    public Activation create() {
        Activation activation = new Activation();
        activation.setKey(getKey());
        activation.setDescription("За получение Х износа");
        activation.setParams(new HashMap<>());
        activation.getParams().put("X", 1);
        return activation;
    }

    @Override
    public void rate(Activation activation) {
        Integer x = (Integer) activation.getParams().get("X");
        activation.setRate(2 * x);
    }
}
