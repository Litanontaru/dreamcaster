package org.dmg.dreamcaster.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class Power extends RepresentationModel<Power> {
    private Long id;
    private String key;
    private Map<String, Object> params;

    private Integer rate;
    private Double multiplier;

    private Effect effect;
    private List<Activation> activations;
    private List<Power> children;
}