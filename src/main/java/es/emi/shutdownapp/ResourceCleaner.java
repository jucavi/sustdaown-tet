package es.emi.shutdownapp;

import jakarta.annotation.PreDestroy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ResourceCleaner {

    @PreDestroy
    public void cleanup() {
        closeDatabaseConnections();
        closeOtherResources();
    }

    private void closeDatabaseConnections() {
        log.info("Closing database connections");
    }

    private void closeOtherResources() {
        log.info("Closing other resources");
    }
}