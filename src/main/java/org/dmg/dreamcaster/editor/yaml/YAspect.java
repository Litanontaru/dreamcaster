package org.dmg.dreamcaster.editor.yaml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class YAspect {
    private String name;
    private Integer base;
    private Map<String, Object> params;

    public String getKey() {
        return name.split(" ")[0];
    }

    public boolean isInstant() {
        return Stream.of(name.split(" ")).anyMatch("instant"::equals);
    }
}
