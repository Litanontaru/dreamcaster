package org.dmg.dreamcaster.editor.yaml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Elements {
    private List<YElement> elements;
    private List<YAspect> aspects;
}
