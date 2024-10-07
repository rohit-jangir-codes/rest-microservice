
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring boot application class
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
public class Application {

    /**
     * Spring boot main application class
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        /* Enable healthcheck properties */
        System.setProperty("management.endpoint.health.show-details", "always");
        System.setProperty("management.endpoints.web.path-mapping.health", "healthcheck");
        System.setProperty("management.health.status.http-mapping.DOWN", "500");
        /* Management endpoint configuration */
        System.setProperty("management.endpoints.web.base-path", "/vos360-cdvr-privatecopy/admin/v1");
        System.setProperty("management.endpoints.web.exposure.include", "ping,health,metrics,info,hawtio,jolokia");
        SpringApplication.run(Application.class, args);
    }
}