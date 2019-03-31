package ampos.restaurant.controller;

import ampos.restaurant.controller.mapper.RestaurantResponseMapper;
import ampos.restaurant.controller.request.BillOrderRequest;
import ampos.restaurant.controller.response.BillOrderResponse;
import ampos.restaurant.controller.response.RestaurantMenuResponse;
import ampos.restaurant.domain.BillOrder;
import ampos.restaurant.service.BillOrderService;
import io.swagger.annotations.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;

@Api(tags = "Bill Order")
@RestController
public class BillOrderController {

	@Autowired
	private BillOrderService billOrderService;

	@Autowired
	private RestaurantResponseMapper restaurantResponseMapper;

//	@ApiOperation("Retrieves a menu by the given ID.")
//	@ApiResponses(value = {
//			@ApiResponse(code = 404, message = "Menu not found."),
//			@ApiResponse(code = 200, message = "OK") })
//	@RequestMapping(method = RequestMethod.GET, value = "/api/menu/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public RestaurantMenuResponse getMenu(@ApiParam(name="id", value = "The ID of the menu.", required = true)
//                                              @PathVariable ObjectId id) {
//
//		return restaurantResponseMapper.toResponse(restaurantMenuService.findbyId(id));
//
//	}

//	@ApiOperation("Retrieves the restaurant menu.")
//	@RequestMapping(method = RequestMethod.GET, value = "/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ListResponseData listAll(@ApiParam(name="page") @RequestParam(value = "page", defaultValue = "0", required = false)  int page,
//									@ApiParam(name="pageSize") @RequestParam(value = "page", defaultValue = "5", required = false)  int pageSize) {
//
//		return restaurantMenuService.listAll(page, pageSize);
//	}

	@ApiOperation("Creates a new order.")
	@RequestMapping(method = RequestMethod.POST,
			value = "/api/order",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BillOrderResponse> createNewOrder(
	        @ApiParam(name="billOrder", value = "Bill order", required = true)
            @RequestBody BillOrderRequest billOrderRequest) {

		BillOrder createNewOrder = billOrderService.create(billOrderRequest);

		return ResponseEntity.created(restaurantMenuURI(createNewOrder.getId()))
				.body(restaurantResponseMapper.toResponseBillOrder(createNewOrder));
	}

//	@ApiOperation("Updates an existing menu.")
//	@RequestMapping(method = RequestMethod.PUT, value = "/api/menu/{id}",
//			consumes = MediaType.APPLICATION_JSON_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<RestaurantMenuResponse> updateMenu(
//			@ApiParam(name="id", value = "The ID of the menu.", required = true) @PathVariable ObjectId id,
//			@ApiParam(name="restaurantMenu", value = "The restaurant menu.", required = true)
//			@RequestBody RestaurantMenuRequest restaurantMenu) {
//
//		RestaurantMenu updatedRestaurantMenu = restaurantMenuService.udpateRestaurantMenu(restaurantMenu, id);
//
//		return ResponseEntity.created(restaurantMenuURI(updatedRestaurantMenu.getId()))
//				.body(restaurantResponseMapper.toResponse(updatedRestaurantMenu));
//	}
//
//    @ApiOperation("Remove a menu by the given ID.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 404, message = "Menu not found."),
//            @ApiResponse(code = 200, message = "OK") })
//    @RequestMapping(method = RequestMethod.DELETE, value = "/api/menu/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity removeMenu(
//            @ApiParam(name="id", value = "The ID of the menu.", required = true)
//            @PathVariable ObjectId id) {
//	    restaurantMenuService.removeOne(id);
//	    return ResponseEntity.status(HttpStatus.OK).build();
//
//    }

	private static URI restaurantMenuURI(ObjectId studentId) {
		return toUri("/api/menu/{id}", studentId);
	}

	private static URI toUri(String path, ObjectId studentId) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path(path)
				.buildAndExpand(studentId).toUri();
	}


}