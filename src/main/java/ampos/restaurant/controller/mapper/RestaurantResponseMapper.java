package ampos.restaurant.controller.mapper;

import ampos.restaurant.controller.response.BillOrderResponse;
import ampos.restaurant.controller.response.RestaurantMenuResponse;
import ampos.restaurant.domain.BillOrder;
import ampos.restaurant.domain.RestaurantMenu;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantResponseMapper {

    public RestaurantMenuResponse toResponse(RestaurantMenu restaurantMenu) {
        return new RestaurantMenuResponse()
                .setId(restaurantMenu.getId())
                .setName(restaurantMenu.getName())
                .setDescription(restaurantMenu.getDescription())
                .setImage(restaurantMenu.getImage())
                .setPrice(restaurantMenu.getPrice())
                .setAdditional(restaurantMenu.getAdditional());

    }

    public List<BillOrderResponse> toResponseBillOrder(BillOrder billOrder) {

        return billOrder.getMenuOrderItems().stream()
                .map(order -> new BillOrderResponse()
                        .setBillNo(billOrder.getBillNo())
                        .setMenuItem(order.getId())
                        .setQuantity(order.getQuantity())
                        .setTotalPrice(order.getPriceWithQuantity())
                        .setOrderTime(billOrder.getOrderTime()))
                .collect(Collectors.toList());


    }
}
