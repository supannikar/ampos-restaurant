package ampos.restaurant.service;

import ampos.restaurant.controller.request.RestaurantMenuRequest;
import ampos.restaurant.domain.RestaurantMenu;
import org.bson.types.ObjectId;

public interface RestaurantMenuService {

	public RestaurantMenu create(RestaurantMenuRequest restaurantMenu);
	public RestaurantMenu udpateRestaurantMenu(RestaurantMenuRequest restaurantMenu, ObjectId id);
	public RestaurantMenu findbyId(ObjectId id);
//	public RestaurantMenu updateStudentCourses(RestaurantMenu student, Collection<Course> courses);
	
}
