package es.emi.shutdownapp;

import jakarta.annotation.PreDestroy;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.ZonedDateTime;

@Controller
@RequestMapping("/app")
@Log4j2
public class ShutdownController {

        private final ShutDownService service;

    public ShutdownController(ShutDownService service) {
        this.service = service;
    }

    @RequestMapping("/now")
        public ResponseEntity<String> now() {
            return ResponseEntity.ok(ZonedDateTime.now().toString());
        }

    @RequestMapping("/exit")
    public ResponseEntity<String> shutdownApp() {
        service.exit();
        return ResponseEntity.ok("Shutdown initiated...");
    }

    @PreDestroy
    public void cleanup() {
        log.info("ShutdownController cleanup completed.");
    }
}
