package org.dmg.dreamcaster.aspects;

import org.dmg.dreamcaster.service.ActivationManagerX;
import org.springframework.stereotype.Component;

@Component
public class PayPsyhoTraumaActivationManager extends ActivationManagerX {
    public PayPsyhoTraumaActivationManager() {
        super("pay_psyho_trauma", "Травмировать характер на Х", 4.0, 0);
    }
}
