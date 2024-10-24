package es.emi.shutdownapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;


/**
 * This class is responsible for cleaning up resources when the application is shut down.
 */
@Component
@Log4j2
public class DatabaseResourceCleaner implements DisposableBean {

//    private final DataSource dataSource;
//
//    public DatabaseResourceCleaner(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
    @Override
    public void destroy() throws Exception {
//        if (dataSource instanceof AutoCloseable) {
//            ((AutoCloseable) dataSource).close();  // Close DataSource
//        }
        log.info("Closing database connections, as if we had any :)");
    }
}