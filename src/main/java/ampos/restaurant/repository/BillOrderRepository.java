package ampos.restaurant.repository;

import ampos.restaurant.domain.BillOrder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillOrderRepository
        extends MongoRepository<BillOrder, ObjectId> {
	
}
