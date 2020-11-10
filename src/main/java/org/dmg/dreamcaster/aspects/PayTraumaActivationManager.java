package org.dmg.dreamcaster.aspects;

import org.dmg.dreamcaster.service.ActivationManagerX;
import org.springframework.stereotype.Component;

@Component
public class PayTraumaActivationManager extends ActivationManagerX {
    public PayTraumaActivationManager() {
        super("pay_trauma", "За получение травмы X", 4.0, 0);
    }
}