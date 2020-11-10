package org.dmg.dreamcaster.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class Effect extends RepresentationModel<Effect> {
    private String key;
    private String description;
    private Map<String, Object> params;

    private Integer rate = 0;
    private Double multiplier = 1.0;
}
