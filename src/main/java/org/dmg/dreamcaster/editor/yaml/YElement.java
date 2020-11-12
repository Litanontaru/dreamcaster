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
public class YElement {
    private String name;
    private Double multiply;
    private Integer base;
    private Map<String, Number> options;
}