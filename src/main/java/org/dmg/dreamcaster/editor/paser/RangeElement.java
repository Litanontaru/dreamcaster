package org.dmg.dreamcaster.editor.paser;

import static java.util.stream.Collectors.toList;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RangeElement implements Element {
    private final List<RangeValue> rangeValues;

    public RangeElement(Map<String, Integer> options) {
        rangeValues = options.entrySet().stream().map(RangeValue::of).collect(toList());
    }

    @Override
    public Integer map(Object key) {
        Integer i = (Integer) key;
        return rangeValues.stream().map(r -> r.map(i)).filter(Objects::nonNull).findFirst().orElse(0);
    }

    @AllArgsConstructor
    private static class RangeValue {
        private final Integer start;
        private final Integer end;
        private final Integer value;

        public static RangeValue of(Map.Entry<String, Integer> entry) {
            int i = entry.getKey().indexOf('-');
            return new RangeValue(
                    Integer.parseInt(entry.getKey().substring(0, i)),
                    Integer.parseInt(entry.getKey().substring(i + 1)),
                    entry.getValue().intValue()
            );
        }

        public Integer map(Integer key) {
            return (key >= start) && (key <= end) ? value : null;
        }
    }
}