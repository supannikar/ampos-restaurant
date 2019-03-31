package ampos.restaurant.service;

import ampos.restaurant.controller.request.RestaurantMenuRequest;
import ampos.restaurant.controller.response.ListResponseData;
import ampos.restaurant.domain.RestaurantMenu;
import org.bson.types.ObjectId;

import java.util.List;

public interface RestaurantMenuService {

	public RestaurantMenu create(RestaurantMenuRequest restaurantMenu);
	public RestaurantMenu udpateRestaurantMenu(RestaurantMenuRequest restaurantMenu, ObjectId id);
	public RestaurantMenu findbyId(ObjectId id);
	public ListResponseData listAll(int page, int pageSize, String name);
	public void removeOne(ObjectId id);
	
}
