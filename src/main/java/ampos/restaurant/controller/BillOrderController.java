package ampos.restaurant.controller;

import ampos.restaurant.controller.mapper.RestaurantResponseMapper;
import ampos.restaurant.controller.request.BillOrderRequest;
import ampos.restaurant.controller.request.MenuItemRequest;
import ampos.restaurant.controller.response.BillOrderResponse;
import ampos.restaurant.domain.BillOrder;
import ampos.restaurant.domain.MenuOrderItem;
import ampos.restaurant.service.BillOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Api(tags = "Bill Order")
@RestController
public class BillOrderController {

	@Autowired
	private BillOrderService billOrderService;

	@Autowired
	private RestaurantResponseMapper restaurantResponseMapper;

	@ApiOperation("Creates a new order.")
	@RequestMapping(method = RequestMethod.POST,
			value = "/api/order",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BillOrderResponse>> createNewOrder(
	        @ApiParam(name="billOrder", value = "Bill order", required = true)
            @RequestBody BillOrderRequest billOrderRequest) {

		BillOrder createNewOrder = billOrderService.create(billOrderRequest);

		return ResponseEntity.created(restaurantMenuURI(createNewOrder.getId()))
				.body(restaurantResponseMapper.toResponseBillOrder(createNewOrder));
	}

	@ApiOperation("Update a bill order")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Order not found."),
			@ApiResponse(code = 200, message = "OK") })
	@RequestMapping(method = RequestMethod.PUT, value = "/api/order/{billNo}/bill",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateBillOrder(@ApiParam(name="billNo", value = "The bill number of the order.")
											  @PathVariable Integer billNo,
										  @RequestBody MenuItemRequest menuItemRequest) {

		BillOrder updateOrder = billOrderService.udpateBillOrder(menuItemRequest, billNo);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(updateOrder);

	}

    @ApiOperation("List order a menu by bill order number")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Order not found."),
            @ApiResponse(code = 200, message = "OK") })
    @RequestMapping(method = RequestMethod.GET, value = "/api/order", produces =
			MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listBillOrder(@ApiParam(name="billNo", value = "The bill number of the order.")
            @RequestParam(value = "billNo", required = false) Integer billNo) {
		if (nonNull(billNo)) {
			BillOrder billOrder = billOrderService.findOrderByBillNo(billNo);
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(billOrder);
		}

	    return ResponseEntity
				.status(HttpStatus.OK)
				.body(billOrderService.listAll());

    }

	private static URI restaurantMenuURI(String id) {
		return toUri("/api/order/{id}", id);
	}

	private static URI toUri(String path, String id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path(path)
				.buildAndExpand(id).toUri();
	}


}