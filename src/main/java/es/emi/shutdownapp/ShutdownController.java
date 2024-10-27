package es.emi.shutdownapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shutdown")
public class ShutdownController {

        private final ShutdownExecutor shutdownExecutor;

        public ShutdownController(ShutdownExecutor shutdownExecutor) {
            this.shutdownExecutor = shutdownExecutor;
        }

        @RequestMapping("/exit")
        public void exit() {
            shutdownExecutor.shutdown();
        }
}
