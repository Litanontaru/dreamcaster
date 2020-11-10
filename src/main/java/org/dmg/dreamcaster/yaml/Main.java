package org.dmg.dreamcaster.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("examples/power.yml")) {
            Base base = yaml.loadAs(inputStream, Base.class);
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
