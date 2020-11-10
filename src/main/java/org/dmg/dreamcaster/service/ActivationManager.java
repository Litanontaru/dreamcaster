package org.dmg.dreamcaster.service;

import org.dmg.dreamcaster.model.Activation;

public interface ActivationManager {
    String getKey();
    Activation create();
    void rate(Activation activation);
}
