package es.emi.shutdownapp;


import jakarta.annotation.PreDestroy;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class ShutDownService {

    private final ApplicationContext applicationContext;
    private final ApplicationEventPublisher eventPublisher;
    private volatile boolean isShutdownInitiated = false;

    public ShutDownService(ApplicationContext applicationContext, ApplicationEventPublisher eventPublisher) {
        this.applicationContext = applicationContext;
        this.eventPublisher = eventPublisher;
    }

    public void exit() {
        if (!isShutdownInitiated) {
            isShutdownInitiated = true;
            log.info("Publishing shutdown event...");
            eventPublisher.publishEvent(new ContextClosedEvent(applicationContext));
            eventPublisher.publishEvent(new ApplicationShutdownEvent(this));
            log.info("Service shutdown published.");

        } else {
            log.warn("Shutdown already initiated.");
        }
    }

    @PreDestroy
    public void cleanup() {
        log.info("ShutDownService cleanup completed.");
    }
}