package org.dmg.dreamcaster.editor.yaml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class YPower {
    private String name;
    private Map<String, Object> effect;
    private Map<String, Object> activation;
}
