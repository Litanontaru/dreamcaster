package org.dmg.dreamcaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DreamcasterApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DreamcasterApplication.class, args);

        ContextAccess.CONTEXT = context.getBean(Context.class);
    }
}
