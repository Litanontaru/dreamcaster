package org.dmg.dreamcaster.editor.paser;

import static java.util.stream.Collectors.toList;

import org.dmg.dreamcaster.editor.yaml.Elements;
import org.dmg.dreamcaster.editor.yaml.PowerList;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Main {
    private static List<String> files = Arrays.asList(
            "elements",
            "aspects",
            "damage"
    );

    public static void main(String[] args) {
        List<Elements> elementsList = files.stream().map(Main::getElements).collect(toList());
        Elements elements = new Elements();
        elements.setElements(elementsList.stream().map(Elements::getElements).filter(Objects::nonNull).flatMap(Collection::stream).collect(toList()));
        elements.setAspects(elementsList.stream().map(Elements::getAspects).filter(Objects::nonNull).flatMap(Collection::stream).collect(toList()));

        PowerList powers;
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("examples/powers.yml")) {
            powers = new Yaml().loadAs(inputStream, PowerList.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        PowerMapper mapper = new PowerMapper(elements);

        for (Power power : mapper.map(powers.getPowers())) {
            System.out.println(power.getName() + ": \t" + power.rate());
        }

        System.out.println();
    }

    private static Elements getElements(String filename) {
        Elements elements;
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("examples/" + filename + ".yml")) {
            elements = new Yaml().loadAs(inputStream, Elements.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return elements;
    }
}
