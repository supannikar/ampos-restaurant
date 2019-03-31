package ampos.restaurant.service;

import ampos.restaurant.controller.request.BillOrderRequest;
import ampos.restaurant.domain.BillOrder;
import ampos.restaurant.domain.RestaurantMenu;
import ampos.restaurant.repository.BillOrderRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class BillOrderServiceImpl implements BillOrderService {

	@Autowired
	private BillOrderRepository billOrderRepository;

	@Autowired
	private RestaurantMenuService restaurantMenuService;

	@Override
	public BillOrder create(BillOrderRequest billOrderRequest) {
		RestaurantMenu restaurantMenu = restaurantMenuService.findbyId(new ObjectId(billOrderRequest.getMenuItem()));
		BillOrder billOrder = new BillOrder()
				.setBillNo(billOrderRequest.getBillNo())
				.setOrderTime(Instant.now())
				.setMenuItem(restaurantMenu.getId())
				.setQuantity(billOrderRequest.getQuantity())
				.setTotalPrice(Math.ceil((double) billOrderRequest.getQuantity() * restaurantMenu.getPrice()));
		return billOrderRepository.save(billOrder);
	}
}
