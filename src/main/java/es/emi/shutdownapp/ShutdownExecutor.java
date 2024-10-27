package es.emi.shutdownapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@Log4j2
public class ShutdownExecutor implements ApplicationListener<ContextClosedEvent>, DisposableBean {

    private final ScheduledExecutorService scheduledExecutorService;
    private boolean isShutdown = false;

    public ShutdownExecutor(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        initiateShutdown();
    }

    private void initiateShutdown() {
        if (!isShutdown) {
            isShutdown = true;
            log.info("Executor shutdown initiating shutdown...");

            // Gracefully shut down the executor
            scheduledExecutorService.shutdown();
            try {
                if (!scheduledExecutorService.awaitTermination(10, TimeUnit.SECONDS)) {
                    log.warn("Executor did not terminate in the specified time.");
                    scheduledExecutorService.shutdownNow(); // Force shutdown if not terminated
                }
            } catch (InterruptedException e) {
                log.error("Shutdown interrupted. Forcing shutdown...");
                scheduledExecutorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
            log.info("Executor shutdown complete.");
        }
    }

    @Override
    public void destroy() {
        initiateShutdown();
        log.info("ShutdownExecutor cleanup completed.");
    }
}
