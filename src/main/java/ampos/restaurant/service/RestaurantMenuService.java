package ampos.restaurant.service;

import ampos.restaurant.controller.request.RestaurantMenuRequest;
import ampos.restaurant.controller.response.ListResponseData;
import ampos.restaurant.domain.RestaurantMenu;
import org.bson.types.ObjectId;

import java.util.List;

public interface RestaurantMenuService {

	public RestaurantMenu create(RestaurantMenuRequest restaurantMenu);
	public RestaurantMenu udpateRestaurantMenu(RestaurantMenuRequest restaurantMenu, String id);
	public RestaurantMenu findbyId(String id);
	public ListResponseData listAll(int page, int pageSize, String name);
	public void removeOne(String id);
	
}
