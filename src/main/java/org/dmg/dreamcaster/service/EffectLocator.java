package org.dmg.dreamcaster.service;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import lombok.RequiredArgsConstructor;
import org.dmg.dreamcaster.model.Effect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EffectLocator {
    private final List<EffectManager> effectManagers;
    private Map<String, EffectManager> map;

    @PostConstruct
    private void init() {
        map = effectManagers.stream().collect(toMap(EffectManager::getKey, identity()));
    }

    public Effect create(String key) {
        return map.get(key).create();
    }

    public void rate(Effect effect) {
        map.get(effect.getKey()).rate(effect);
    }

    public List<String> effects() {
        return map.keySet().stream().sorted().collect(toList());
    }
}