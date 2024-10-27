package es.emi.shutdownapp;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShutDownAppApplication {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(ShutDownAppApplication.class, args);
    }

    public static void exitApplication() {
        if (context != null) {
            SpringApplication.exit(context, () -> 0);
        } else {
            throw new IllegalStateException("Context is not initialized.");
        }
    }


    @Bean
    public CommandLineRunner commandLineRunner(ConfigurableApplicationContext ctx) {
        return args -> {
            context = ctx;
        };
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("ShutDownAppApplication cleanup completed.");
    }
}
