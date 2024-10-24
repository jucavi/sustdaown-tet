package es.emi.shutdownapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ShutDownAppApplication {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {

        context = SpringApplication.run(ShutDownAppApplication.class, args);
    }

    public static void exitApplication() {
        SpringApplication.exit(context, () -> 0);
    }
}
