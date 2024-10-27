package es.emi.shutdownapp;

import jakarta.annotation.PreDestroy;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.ZonedDateTime;

@Controller
@RequestMapping("/app")
@Log4j2
public class ShutdownController {

//    private final ShutDownService service;
//
//    public ShutdownController(ShutDownService service) {
//        this.service = service;
//    }

    @GetMapping("/now")
    public ResponseEntity<String> now() {
        log.info("Current time requested.");
        return ResponseEntity.ok(ZonedDateTime.now().toString());
    }

//    @GetMapping("/exit")
//    public ResponseEntity<String> shutdownApp() {
//        service.exit();
//        return ResponseEntity.ok("Controller shutdown done...");
//    }

    @GetMapping("/shutdown")
    public ResponseEntity<String> shutdown() {
        ShutDownAppApplication.exitApplication();
        return ResponseEntity.ok("Application is shutting down...");
    }

    @PreDestroy
    public void cleanup() {
        log.info("ShutdownController cleanup completed.");
    }
}
