package org.dmg.dreamcaster.yaml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Power {
    private Map<String, Object> effect;
    private Map<String, Object> activation;
}
