package ampos.restaurant.config;

import ampos.restaurant.controller.mapper.RestaurantResponseMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmposRestaurantApiConfig {

    @Bean
    public RestaurantResponseMapper restaurantMenuResponseMapper() {
        return new RestaurantResponseMapper();
    }
}
