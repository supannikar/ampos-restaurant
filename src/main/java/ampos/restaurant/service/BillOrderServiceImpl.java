package ampos.restaurant.service;

import ampos.restaurant.controller.request.BillOrderRequest;
import ampos.restaurant.controller.response.BillOrderResponse;
import ampos.restaurant.domain.BillOrder;
import ampos.restaurant.domain.MenuOrderItem;
import ampos.restaurant.domain.RestaurantMenu;
import ampos.restaurant.repository.BillOrderDao;
import ampos.restaurant.repository.BillOrderRepository;
import com.mongodb.QueryBuilder;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.QueryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillOrderServiceImpl implements BillOrderService {

	@Autowired
	private BillOrderRepository billOrderRepository;

	@Autowired
	private RestaurantMenuService restaurantMenuService;

	@Override
	public BillOrder create(BillOrderRequest billOrderRequest) {

		List<MenuOrderItem> menuOrderItems = billOrderRequest.getMenuItem().stream()
				.map(item -> {
					RestaurantMenu restaurantMenu =
					restaurantMenuService.findbyId(new ObjectId(item.getId()));
					Double priceWithQuantity =
							Math.ceil((double) item.getQuantity() * restaurantMenu.getPrice());

					return new MenuOrderItem()
							.setId(restaurantMenu.getId().toHexString())
							.setQuantity(item.getQuantity())
							.setPriceWithQuantity(priceWithQuantity);
				}).collect(Collectors.toList());

		Double totalPrice = menuOrderItems.stream()
				.map(MenuOrderItem::getPriceWithQuantity)
				.reduce(0.0, Double::sum);

		BillOrder billOrder = new BillOrder()
				.setBillNo(billOrderRequest.getBillNo())
				.setOrderTime(Instant.now())
				.setMenuOrderItems(menuOrderItems)
				.setTotalPrice(totalPrice);
		return billOrderRepository.save(billOrder);
	}

	@Override
	public BillOrder findbyBillNo(final Integer billNo) {
		BillOrder billOrder = billOrderDao.createQuery().field("billNo").equal(billNo).get();
		return billOrder;
	}

	@Override
	public List<BillOrderResponse> listAll() {
		return null;
	}
}
