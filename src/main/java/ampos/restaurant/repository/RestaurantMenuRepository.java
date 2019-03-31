package ampos.restaurant.repository;

import ampos.restaurant.domain.RestaurantMenu;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantMenuRepository
        extends MongoRepository<RestaurantMenu, ObjectId> {
	
}
