package org.dmg.dreamcaster.service;

import org.dmg.dreamcaster.model.Effect;

public interface EffectManager {
    String getKey();
    Effect create();
    void rate(Effect effect);
}
