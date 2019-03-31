package ampos.restaurant.controller.mapper;

import ampos.restaurant.controller.response.BillOrderResponse;
import ampos.restaurant.controller.response.RestaurantMenuResponse;
import ampos.restaurant.domain.BillOrder;
import ampos.restaurant.domain.RestaurantMenu;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RestaurantResponseMapper {

    public RestaurantMenuResponse toResponse(RestaurantMenu restaurantMenu) {
        return new RestaurantMenuResponse()
                .setId(restaurantMenu.getId().toHexString())
                .setName(restaurantMenu.getName())
                .setDescription(restaurantMenu.getDescription())
                .setImage(restaurantMenu.getImage())
                .setPrice(restaurantMenu.getPrice())
                .setAdditional(restaurantMenu.getAdditional());

    }

    public BillOrderResponse toResponseBillOrder(BillOrder billOrder) {
        return new BillOrderResponse().setBillNo(billOrder.getBillNo())
                .setMenuItem(billOrder.getMenuItem().toHexString())
                .setOrderTime(billOrder.getOrderTime())
                .setTotalPrice(billOrder.getTotalPrice())
                .setQuantity(billOrder.getQuantity());

    }
}
