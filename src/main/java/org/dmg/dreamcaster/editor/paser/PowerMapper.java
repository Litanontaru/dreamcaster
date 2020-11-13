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

@SuppressWarnings("unchecked")
public class PowerMapper {
    private static final String RESERVE = "reserve";
    private static final String THREAT = "threat";
    private static final String FACTOR = "factor";

    private final Map<String, YAspect> aspectMap;
    private final Map<String, Element> elementMap;

    PowerMapper(Elements elements) {
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
        if (key.equals(RESERVE)) {
            return mapReserve(value);
        } else if (key.equals(THREAT)) {
            return mapThreat(value);
        }

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
                    : Element.of(((Map<String, Integer>) e));

            params.put(entry.getKey(), element.map(values.get(i)));
            multipliers.add(element.multiplier(values.get(i)));
        }

        Double multiplier = multipliers.stream().filter(Objects::nonNull).findFirst().orElse(null);
        return new Aspect(y.getName(), base, params, multiplier);
    }

    private Aspect mapReserve(Object value) {
        int sum = ((Map<String, Object>) value)
                .entrySet()
                .stream()
                .map(e -> map(e.getKey(), e.getValue()))
                .mapToInt(Aspect::rate)
                .sum();
        return new Aspect(RESERVE, sum / 4, emptyMap(), null);
    }

    private Aspect mapThreat(Object value) {
        Map<String, Object> map = (Map<String, Object>) value;
        List<Integer> rates = map
                .entrySet()
                .stream()
                .filter(e -> !e.getKey().equals(FACTOR))
                .map(e -> map(e.getKey(), e.getValue()))
                .map(Aspect::rate)
                .collect(toList());
        Double factor = (Double) map.getOrDefault(FACTOR, 1.0);

        int sum = rates.stream().mapToInt(i -> i).sum();
        int max = rates.stream().mapToInt(i -> i).max().orElse(0);
        int rate = (int) ((sum - max) * factor + max);

        return new Aspect(THREAT, rate, emptyMap(), null);
    }
}