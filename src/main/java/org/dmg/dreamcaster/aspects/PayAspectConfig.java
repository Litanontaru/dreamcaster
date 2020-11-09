package org.dmg.dreamcaster.aspects;

import org.dmg.dreamcaster.model.Activation;
import org.dmg.dreamcaster.model.ActivationX;
import org.dmg.dreamcaster.model.Formula;
import org.dmg.dreamcaster.model.Formula1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class PayAspectConfig {
    @Bean
    public Formula payWearAspectFormula() {
        return new Formula1("pay wear aspect", 2, 0);
    }

    @Bean
    public Supplier<Activation> payWearAspect() {
        return () -> new ActivationX("pay wear aspect");
    }
}
