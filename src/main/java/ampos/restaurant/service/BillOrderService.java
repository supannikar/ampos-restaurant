package ampos.restaurant.service;

import ampos.restaurant.controller.request.BillOrderRequest;
import ampos.restaurant.controller.request.MenuItemRequest;
import ampos.restaurant.controller.response.BillOrderResponse;
import ampos.restaurant.domain.BillOrder;

import java.util.List;

public interface BillOrderService {

	public BillOrder create(BillOrderRequest billOrderRequest);
	public BillOrder udpateBillOrder(MenuItemRequest menuItemRequest, Integer billNo);
	public BillOrder findOrderByBillNo(Integer billNo);
	public List<BillOrder> listAll();
//	public void removeOne(ObjectId id);
	
}
