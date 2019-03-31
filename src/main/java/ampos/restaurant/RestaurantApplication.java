package ampos.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The main application class for the Swagger demo project.
 */
@ComponentScan
@SpringBootApplication
public class RestaurantApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RestaurantApplication.class, args);
	}
}