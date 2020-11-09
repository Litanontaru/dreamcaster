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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContextImpl implements Context {
    private final List<Formula> formulas;

    private Map<String, Formula> mapped;

    @PostConstruct
    private void init() {
        mapped = formulas.stream().collect(Collectors.toMap(Formula::getName, identity()));
    }

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
        return mapped.get(name);
    }
}
