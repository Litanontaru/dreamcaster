package org.dmg.dreamcaster.editor.paser;

import static java.util.Collections.emptyMap;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import org.dmg.dreamcaster.editor.yaml.Elements;
import org.dmg.dreamcaster.editor.yaml.YAspect;
import org.dmg.dreamcaster.editor.yaml.YElement;
import org.dmg.dreamcaster.editor.yaml.YPower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PowerMapper {
    private final Map<String, YAspect> aspectMap;
    private final Map<String, Element> elementMap;

    public PowerMapper(Elements elements) {
        aspectMap = elements.getAspects().stream().collect(toMap(YAspect::getKey, identity()));
        elementMap = elements.getElements().stream().collect(toMap(YElement::getName, Element::of));
    }

    public List<Power> map(List<YPower> powers) {
        return powers.stream().map(this::map).collect(toList());
    }

    private Power map(YPower power) {
        return new Power(
                power.getEffect().entrySet().stream().map(e -> map(e.getKey(), e.getValue())).collect(toList()),
                power.getActivation().entrySet().stream().map(e -> map(e.getKey(), e.getValue())).collect(toList()),
                Power.SIMPLE
        );
    }

    private Aspect map(String key, Object value) {
        YAspect y = aspectMap.get(key);
        Integer base = y.getBase() == null ? 0 : y.getBase();
        if (value == null) {
            return new Aspect(y.getName(), base, emptyMap(), null);
        }

        Map<String, Integer> params = new LinkedHashMap<>(y.getParams().size());
        List<Object> values = value instanceof List ? (List<Object>) value : Collections.singletonList(value);
        List<Map.Entry<String, Object>> entries = new ArrayList<>(y.getParams().entrySet());
        List<Double> multipliers = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            Map.Entry<String, Object> entry = entries.get(i);
            Object e = entry.getValue();
            Element element = e instanceof String
                    ? elementMap.get(e)
                    : Element.of(((Map<String, Number>) e));

            params.put(entry.getKey(), element.map(values.get(i)));
            multipliers.add(element.multiplier(values.get(i)));
        }

        Double multiplier = multipliers.stream().filter(Objects::nonNull).findFirst().orElse(null);
        return new Aspect(y.getName(), base, params, multiplier);
    }
}