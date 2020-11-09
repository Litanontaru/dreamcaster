package org.dmg.dreamcaster;

import static java.util.function.Function.identity;

import lombok.RequiredArgsConstructor;
import org.dmg.dreamcaster.model.Activation;
import org.dmg.dreamcaster.model.Effect;
import org.dmg.dreamcaster.model.Formula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContextImpl implements Context {
    private final List<Formula> formulas;
    private final List<Supplier<Activation>> activations;

    private Map<String, Formula> mFormulas;
    private Map<String, Supplier<Activation>> mActivations;

    @PostConstruct
    private void init() {
        mFormulas = formulas.stream().collect(Collectors.toMap(Formula::getName, identity()));
        mActivations = activations.stream().collect(Collectors.toMap(f -> f.get().getName(), identity()));
    }

    @Override
    public Effect createEffect(String name) {
        return null;
    }

    @Override
    public Activation createActivation(String name) {
        return mActivations.get(name).get();
    }

    @Override
    public Function<int[], Integer> formula(String name) {
        return mFormulas.get(name);
    }
}
