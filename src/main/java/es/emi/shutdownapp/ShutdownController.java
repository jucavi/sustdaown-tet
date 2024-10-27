package es.emi.shutdownapp;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.ZonedDateTime;

@Controller
@RequestMapping("/app")
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
        public ResponseEntity<String> exit() {
            service.exit();
            return ResponseEntity.ok("Shutdown initiated...");
        }
}
