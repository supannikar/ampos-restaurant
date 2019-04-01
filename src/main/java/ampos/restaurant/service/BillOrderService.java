package ampos.restaurant.service;

import ampos.restaurant.controller.request.BillOrderRequest;
import ampos.restaurant.controller.response.BillOrderResponse;
import ampos.restaurant.domain.BillOrder;

import java.util.List;

public interface BillOrderService {

	public BillOrder create(BillOrderRequest billOrderRequest);
//	public RestaurantMenu udpateRestaurantMenu(RestaurantMenuRequest restaurantMenu, ObjectId id);
	public BillOrder findbyBillNo(Integer billNo);
	public List<BillOrderResponse> listAll();
//	public void removeOne(ObjectId id);
	
}
