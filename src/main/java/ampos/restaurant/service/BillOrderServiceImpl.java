package ampos.restaurant.service;

import ampos.restaurant.controller.mapper.RestaurantResponseMapper;
import ampos.restaurant.controller.request.BillOrderRequest;
import ampos.restaurant.controller.request.MenuItemRequest;
import ampos.restaurant.domain.BillOrder;
import ampos.restaurant.domain.MenuOrderItem;
import ampos.restaurant.domain.RestaurantMenu;
import ampos.restaurant.exceptions.NotFoundException;
import ampos.restaurant.repository.BillOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
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

		RestaurantMenu restaurantMenu = restaurantMenuService.findbyId(billOrderRequest.getId());

		if (nonNull(restaurantMenu)) {
			Double totalPrice =
					Math.ceil((double) billOrderRequest.getQuantity() * restaurantMenu.getPrice());
			MenuOrderItem menuOrderItem = new MenuOrderItem()
					.setId(restaurantMenu.getId())
					.setQuantity(billOrderRequest.getQuantity())
					.setPriceWithQuantity(totalPrice);

			BillOrder billOrder = new BillOrder()
					.setBillNo(getNextSequence("BillOrder"))
					.setOrderTime(Instant.now())
					.setMenuOrderItems(Collections.singletonList(menuOrderItem))
					.setTotalPrice(totalPrice);
			return billOrderRepository.save(billOrder);
		} else {
			throw new NotFoundException("Not found this menu id: " + billOrderRequest.getId());
		}
	}

	@Override
	public BillOrder udpateBillOrder(MenuItemRequest menuItemRequest,
									 Integer billNo) {

		BillOrder existBillOrder = findOrderByBillNo(billNo);
		RestaurantMenu restaurantMenu =
				restaurantMenuService.findbyId(menuItemRequest.getId());

		if (isNull(restaurantMenu)) {
			throw new NotFoundException("Not found menu id: " + menuItemRequest.getId());
		}

		if (nonNull(existBillOrder)) {

			if (!existBillOrder.getMenuOrderItems().isEmpty()) {

				Optional<MenuOrderItem> orderItem = existBillOrder.getMenuOrderItems().stream()
						.filter(item -> item.getId().equalsIgnoreCase(menuItemRequest.getId()))
						.findAny();

				if (orderItem.isPresent()) {
					if (menuItemRequest.getAction().equals(MenuItemRequest.LineItemAction.UPDATE)) {
						orderItem.get().setQuantity(menuItemRequest.getQuantity());
						Double priceWithQuantity =
								Math.ceil((double) menuItemRequest.getQuantity() * restaurantMenu.getPrice());
						orderItem.get().setPriceWithQuantity(priceWithQuantity);
					}

					if (menuItemRequest.getAction().equals(MenuItemRequest.LineItemAction.ADD)) {
						Integer newQuantity = menuItemRequest.getQuantity() + orderItem.get().getQuantity();
						orderItem.get().setQuantity(newQuantity);

						Double priceWithQuantity = Math.ceil((double) newQuantity * restaurantMenu.getPrice());
						orderItem.get().setPriceWithQuantity(priceWithQuantity);
					}

					if (menuItemRequest.getAction().equals(MenuItemRequest.LineItemAction.DELETE)) {
						existBillOrder.getMenuOrderItems().remove(orderItem.get());
					}

				} else {
					MenuOrderItem newItem = new MenuOrderItem().setId(menuItemRequest.getId())
							.setQuantity(menuItemRequest.getQuantity());
					Double priceWithQuantity =
							Math.ceil((double) menuItemRequest.getQuantity() * restaurantMenu.getPrice());
					newItem.setPriceWithQuantity(priceWithQuantity);
					existBillOrder.getMenuOrderItems().add(newItem);
				}

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
			query.addCriteria(Criteria.where("billNo").is(billNo))
					.addCriteria(Criteria.where("menuOrderItems._id").exists(true));
		}

		return mongoOperations.findOne(query, BillOrder.class);
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
