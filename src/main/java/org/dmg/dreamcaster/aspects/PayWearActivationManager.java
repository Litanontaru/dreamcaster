package org.dmg.dreamcaster.aspects;

import org.dmg.dreamcaster.service.ActivationManagerX;
import org.springframework.stereotype.Component;

@Component
public class PayWearActivationManager extends ActivationManagerX {
    public PayWearActivationManager() {
        super("pay_wear", "За получение Х износа", 2.0, 0);
    }
}
