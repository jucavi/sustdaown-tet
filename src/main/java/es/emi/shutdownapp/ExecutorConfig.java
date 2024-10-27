package es.emi.shutdownapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class ExecutorConfig {

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        // Create a ScheduledExecutorService with a pool of threads
        return Executors.newScheduledThreadPool(10);
    }
}