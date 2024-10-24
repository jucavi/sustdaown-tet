package es.emi.shutdownapp;


import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@EnableScheduling
public class ShutDownService {

    @Scheduled(fixedRate = 10000)
    public void checkCondition() {
        if (exhaustRabbitCalls()) {
            ShutDownAppApplication.exitApplication();
        }
    }

    private boolean exhaustRabbitCalls() {

        // Calls to RabbitMQ with retry before crashing
        for (int i = 0; i < 15; i++) {
            callRabbitMQ();
        }

        return true;
    }

    private void callRabbitMQ() {

        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("Calling RabbitMQ");
    }
}
