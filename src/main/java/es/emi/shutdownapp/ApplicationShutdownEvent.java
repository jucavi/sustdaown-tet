package es.emi.shutdownapp;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;


public class ApplicationShutdownEvent extends ApplicationEvent {
    public ApplicationShutdownEvent(Object source) {
        super(source);
    }
}
