package org.dmg.dreamcaster.yaml;

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
    private List<Aspect> aspects;
    private List<Effect> effects;
}
