package org.dmg.dreamcaster;

import lombok.RequiredArgsConstructor;
import org.dmg.dreamcaster.model.Activation;
import org.dmg.dreamcaster.model.Effect;
import org.dmg.dreamcaster.model.Formula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContextImpl implements Context {
    private final List<Formula> formulas;

    @Override
    public Effect createEffect(String name) {
        return null;
    }

    @Override
    public Activation createActivation(String name) {
        return null;
    }

    @Override
    public Function<int[], Integer> formula(String name) {
        return formulas
                .stream()
                .filter(f -> f.getName().equals(name))
                .findFirst()
                .get();
    }
}
