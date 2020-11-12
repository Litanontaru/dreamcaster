package org.dmg.dreamcaster.editor.paser;

import org.dmg.dreamcaster.editor.yaml.Elements;
import org.dmg.dreamcaster.editor.yaml.PowerList;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        Elements elements;
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("examples/elements.yml")) {
            elements = new Yaml().loadAs(inputStream, Elements.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        PowerList powers;
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("examples/powers.yml")) {
            powers = new Yaml().loadAs(inputStream, PowerList.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        PowerMapper mapper = new PowerMapper(elements);

        for (Power power : mapper.map(powers.getPowers())) {
            System.out.println(power.rate());
        }

        System.out.println();
    }
}
