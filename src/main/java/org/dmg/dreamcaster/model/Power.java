package org.dmg.dreamcaster.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class Power extends RepresentationModel<Power> {
    private Long id;
    private String key;
    private Map<String, Object> params = new HashMap<>();

    private Integer rate = 0;
    private Double multiplier = 1.0;

    private Effect effect;
    private List<Activation> activations = new ArrayList<>();
    private List<Power> children;
}