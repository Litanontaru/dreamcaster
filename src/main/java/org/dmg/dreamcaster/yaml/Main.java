package org.dmg.dreamcaster.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("examples/elements.yml")) {
            Elements elements = yaml.loadAs(inputStream, Elements.class);
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("examples/powers.yml")) {
            PowerList powers = yaml.loadAs(inputStream, PowerList.class);
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
