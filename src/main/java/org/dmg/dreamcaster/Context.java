package org.dmg.dreamcaster;

import org.dmg.dreamcaster.model.Activation;
import org.dmg.dreamcaster.model.Effect;

import java.util.function.Function;

public interface Context {
    Effect createEffect(String name);

    Activation createActivation(String name);

    Function<int[], Integer> formula(String name);
}