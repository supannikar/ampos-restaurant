package ampos.restaurant.repository;

import ampos.restaurant.domain.RestaurantMenu;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RestaurantMenuRepository
        extends MongoRepository<RestaurantMenu, ObjectId> {
	
}
