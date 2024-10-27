package es.emi.shutdownapp;

import org.springframework.context.ApplicationEvent;

public class ApplicationShutdownEvent extends ApplicationEvent {
    public ApplicationShutdownEvent(Object source) {
        super(source);
    }
}
