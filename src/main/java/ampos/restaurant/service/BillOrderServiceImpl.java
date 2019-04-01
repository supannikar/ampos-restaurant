package ampos.restaurant.service;

import ampos.restaurant.controller.mapper.RestaurantResponseMapper;
import ampos.restaurant.controller.request.BillOrderRequest;
import ampos.restaurant.controller.request.MenuItemRequest;
import ampos.restaurant.controller.response.BillOrderResponse;
import ampos.restaurant.domain.BillOrder;
import ampos.restaurant.domain.MenuOrderItem;
import ampos.restaurant.domain.RestaurantMenu;
import ampos.restaurant.repository.BillOrderRepository;
import com.mongodb.QueryBuilder;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.QueryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class BillOrderServiceImpl implements BillOrderService {

	@Autowired
	private BillOrderRepository billOrderRepository;

	@Autowired
	private RestaurantMenuService restaurantMenuService;

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private RestaurantResponseMapper restaurantResponseMapper;

	@Override
	public BillOrder create(BillOrderRequest billOrderRequest) {

		List<MenuOrderItem> menuOrderItems = billOrderRequest.getMenuItem().stream()
				.map(item -> {
					RestaurantMenu restaurantMenu =
					restaurantMenuService.findbyId(item.getId());
					Double priceWithQuantity =
							Math.ceil((double) item.getQuantity() * restaurantMenu.getPrice());

					return new MenuOrderItem()
							.setId(restaurantMenu.getId())
							.setQuantity(item.getQuantity())
							.setPriceWithQuantity(priceWithQuantity);
				}).collect(Collectors.toList());

		Double totalPrice = menuOrderItems.stream()
				.map(MenuOrderItem::getPriceWithQuantity)
				.reduce(0.0, Double::sum);

		BillOrder billOrder = new BillOrder()
				.setBillNo(getNextSequence("BillOrder"))
				.setOrderTime(Instant.now())
				.setMenuOrderItems(menuOrderItems)
				.setTotalPrice(totalPrice);
		return billOrderRepository.save(billOrder);
	}

	@Override
	public BillOrder udpateBillOrder(MenuItemRequest menuItemRequest,
									 Integer billNo) {

		BillOrder existBillOrder = findOrderByBillNo(billNo);
		if (nonNull(existBillOrder)) {
			if (!existBillOrder.getMenuOrderItems().isEmpty()) {
				existBillOrder.getMenuOrderItems().forEach(item -> {
					RestaurantMenu restaurantMenu =
							restaurantMenuService.findbyId(item.getId());

					if (item.getId().equals(menuItemRequest.getId())) {
						if ("UPDATE".equalsIgnoreCase(menuItemRequest.getOperation())) {
							item.setQuantity(menuItemRequest.getQuantity());

							Double priceWithQuantity =
									Math.ceil((double) item.getQuantity() * restaurantMenu.getPrice());
							item.setPriceWithQuantity(priceWithQuantity);
						} else if ("REMOVE".equalsIgnoreCase(menuItemRequest.getOperation())) {
							existBillOrder.getMenuOrderItems().remove(item);
						}

					} else {
						MenuOrderItem newItem = new MenuOrderItem().setId(menuItemRequest.getId())
								.setQuantity(menuItemRequest.getQuantity());
						Double priceWithQuantity =
								Math.ceil((double) item.getQuantity() * restaurantMenu.getPrice());
						newItem.setPriceWithQuantity(priceWithQuantity);

						existBillOrder.getMenuOrderItems().add(newItem);
					}
				});
			} else {
				existBillOrder.setMenuOrderItems(Collections.singletonList(new MenuOrderItem()
						.setId(menuItemRequest.getId())
						.setQuantity(menuItemRequest.getQuantity())));
			}

		}

		Double totalPrice = existBillOrder.getMenuOrderItems().stream()
		.map(MenuOrderItem::getPriceWithQuantity)
		.reduce(0.0, Double::sum);

		existBillOrder
				.setOrderTime(Instant.now())
				.setTotalPrice(totalPrice);
		return billOrderRepository.save(existBillOrder);
	}

	@Override
	public BillOrder findOrderByBillNo(final Integer billNo) {
		Query query = new Query();
		if (nonNull(billNo)) {
			query.addCriteria(Criteria.where("billNo").is(billNo));
		}

		BillOrder billOrder = mongoOperations.findOne(query, BillOrder.class);
		return billOrder;
	}

	public BillOrder findOrderByBillNoAndItemId(final Integer billNo, final String menuItemId) {
		Query query = new Query();
			query.addCriteria(Criteria.where("billNo").is(billNo)
					.and("menuOrderItems.id").is(menuItemId));
		BillOrder billOrder = mongoOperations.findOne(query, BillOrder.class);
		return billOrder;
	}

	@Override
	public List<BillOrder> listAll() {
		return mongoOperations.findAll(BillOrder.class);
	}

	public int getNextSequence(String seqName)
	{
		BillOrder counter = mongoOperations.findAndModify(new Query(Criteria.where("_id").is(seqName)),
				new Update().inc("billNo",1),
				options().returnNew(true).upsert(true),
				BillOrder.class);
		return counter.getBillNo();
	}
}
