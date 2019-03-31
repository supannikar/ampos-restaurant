package ampos.restaurant.service;

import ampos.restaurant.controller.request.BillOrderRequest;
import ampos.restaurant.controller.request.RestaurantMenuRequest;
import ampos.restaurant.controller.response.ListResponseData;
import ampos.restaurant.domain.BillOrder;
import ampos.restaurant.domain.RestaurantMenu;
import org.bson.types.ObjectId;

public interface BillOrderService {

	public BillOrder create(BillOrderRequest billOrderRequest);
//	public RestaurantMenu udpateRestaurantMenu(RestaurantMenuRequest restaurantMenu, ObjectId id);
//	public RestaurantMenu findbyId(ObjectId id);
//	public ListResponseData listAll(int page, int pageSize, String name);
//	public void removeOne(ObjectId id);
	
}
