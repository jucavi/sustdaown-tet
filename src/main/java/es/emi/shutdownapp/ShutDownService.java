package es.emi.shutdownapp;


import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
@EnableScheduling
public class ShutDownService {

    private final ShutdownExecutor executor;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public ShutDownService(ShutdownExecutor executor) {
        log.info("Call it...");
        this.executor = executor;
    }


    public void exit() {
//            ShutDownAppApplication.exitApplication();

        log.info("Application will shut down due to a persistent communication failure...");

        // Delay shutdown to allow the current HTTP request to complete
        scheduler.schedule(() -> {
            try {
                executor.shutdown();
                log.info("Application shutdown complete.");
            } catch (Exception e) {
                log.error("Error shutting down application: {}", e.getMessage());
            }
        }, 4, TimeUnit.SECONDS);
    }
}
