package org.dmg.dreamcaster.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class Activation extends RepresentationModel<Activation> {
    private String key;
    private String description;
    private Map<String, Object> params = new HashMap<>();

    private Integer rate;
}