package es.emi.shutdownapp;


import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@WebFilter("/*")
public class ShutdownFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(ShutdownFilter.class);
    private static volatile boolean isShuttingDown = false;

    public static void initiateShutdown() {
        isShuttingDown = true;
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isShuttingDown) {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Application is shutting down.");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        log.info("ShutdownFilter cleanup completed.");
    }
}