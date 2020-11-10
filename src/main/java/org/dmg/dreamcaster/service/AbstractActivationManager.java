package org.dmg.dreamcaster.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.dmg.dreamcaster.model.Activation;

@RequiredArgsConstructor
abstract public class AbstractActivationManager implements ActivationManager {
    @Getter
    private final String key;

    private final String description;

    @Override
    public Activation create() {
        Activation activation = new Activation();
        activation.setKey(key);
        activation.setDescription(description);
        return activation;
    }
}
