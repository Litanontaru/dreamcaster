package org.dmg.dreamcaster.service;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import lombok.RequiredArgsConstructor;
import org.dmg.dreamcaster.model.Activation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivationLocator {
    private final List<ActivationManager> effectManagers;
    private Map<String, ActivationManager> map;

    @PostConstruct
    private void init() {
        map = effectManagers.stream().collect(toMap(ActivationManager::getKey, identity()));
    }

    public Activation create(String key) {
        return map.get(key).create();
    }

    public void rate(Activation effect) {
        map.get(effect.getKey()).rate(effect);
    }

    public List<String> activations() {
        return map.keySet().stream().sorted().collect(toList());
    }
}
