package org.dmg.dreamcaster.aspects;

import org.dmg.dreamcaster.model.Formula;
import org.dmg.dreamcaster.model.Formula1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayAspectConfig {
    @Bean
    public Formula payWearAspectFormula() {
        return new Formula1("pay wear aspect", 2, 0);
    }
}
