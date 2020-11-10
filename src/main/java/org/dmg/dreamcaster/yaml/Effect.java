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
public class Effect {
    private String name;
    private Integer base;
    private Map<String, Object> params;
}
