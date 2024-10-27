package es.emi.shutdownapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ShutdownExecutor implements ExitCodeGenerator {

    private final ConfigurableApplicationContext context;

    public ShutdownExecutor(ConfigurableApplicationContext context) {
        this.context = context;
    }

    public void shutdown() {

        try {
            log.info("Shutting down the application...");
            //context.close();
            SpringApplication.exit(context, this);

        } catch (Exception e) {
            log.error("Error shutting down the application: {}", e.getMessage());
            throw new RuntimeException("Application shutdown due to an error", e);
        }
    }

    @Override
    public int getExitCode() {
        return 0;
    }
}
