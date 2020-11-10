package org.dmg.dreamcaster.service;

import org.dmg.dreamcaster.model.Power;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PowerStore {
    private final Map<Long, Power> powers = new HashMap<>();

    public void save(Power power) {
        if (power.getId() == null) {
            long newId = powers.keySet().stream().mapToLong(t -> t).max().orElse(-1) + 1;
            power.setId(newId);
        }
        powers.put(power.getId(), power);
    }

    public Power get(Long id) {
        return powers.get(id);
    }
}
