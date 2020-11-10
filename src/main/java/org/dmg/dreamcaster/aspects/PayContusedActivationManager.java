package org.dmg.dreamcaster.aspects;

import org.dmg.dreamcaster.service.ActivationManager0;
import org.springframework.stereotype.Component;

@Component
public class PayContusedActivationManager extends ActivationManager0 {
    public PayContusedActivationManager() {
        super("pay_contused", "За контузию или беспамятство", 6);
    }
}
