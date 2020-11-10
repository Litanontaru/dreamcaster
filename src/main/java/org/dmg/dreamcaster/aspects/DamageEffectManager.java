package org.dmg.dreamcaster.aspects;

import org.dmg.dreamcaster.model.Effect;
import org.dmg.dreamcaster.service.EffectManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class DamageEffectManager implements EffectManager {
    @Override
    public String getKey() {
        return "Повредить здоровье";
    }

    @Override
    public Effect create() {
        Effect effect = new Effect();
        effect.setKey(getKey());
        effect.setDescription("Нанести N/M урона здоровью по цели");
        effect.setParams(new HashMap<>());
        effect.getParams().put("N", 0);
        effect.getParams().put("M", 0);
        return effect;
    }

    @Override
    public void rate(Effect effect) {
        Integer n = (Integer) effect.getParams().get("N");
        Integer m = (Integer) effect.getParams().get("M");
        effect.setRate(2 * n + 2 * m);
    }
}