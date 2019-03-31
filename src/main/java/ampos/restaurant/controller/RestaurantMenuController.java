package ampos.restaurant.controller;

import ampos.restaurant.controller.mapper.RestaurantResponseMapper;
import ampos.restaurant.controller.request.RestaurantMenuRequest;
import ampos.restaurant.controller.response.ListResponseData;
import ampos.restaurant.controller.response.RestaurantMenuResponse;
import ampos.restaurant.domain.RestaurantMenu;
import ampos.restaurant.service.RestaurantMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.net.URI;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(tags = "Menu")
@RestController
public class RestaurantMenuController {

	@Autowired
	private RestaurantMenuService restaurantMenuService;

	@Autowired
	private RestaurantResponseMapper restaurantResponseMapper;

	@ApiOperation("Retrieves a menu by the given ID.")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Menu not found."),
			@ApiResponse(code = 200, message = "OK") })
	@RequestMapping(method = RequestMethod.GET, value = "/api/menu/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestaurantMenuResponse getMenu(@ApiParam(name="id", value = "The ID of the menu.", required = true)
                                              @PathVariable ObjectId id) {

		return restaurantResponseMapper.toResponse(restaurantMenuService.findbyId(id));

	}

	@ApiOperation("Retrieves the restaurant menu.")
	@RequestMapping(method = RequestMethod.GET, value = "/api/menu", produces = MediaType.APPLICATION_JSON_VALUE)
	public ListResponseData listAll(@ApiParam(name="page") @RequestParam(value = "page", defaultValue = "0", required = false)  int page,
									@ApiParam(name="pageSize") @RequestParam(value = "page", defaultValue = "5", required = false)  int pageSize,
									@ApiParam(name="name") @RequestParam(value = "name", required = false) String name) {

		return restaurantMenuService.listAll(page, pageSize, name);
	}

	@ApiOperation("Creates a new menu.")
	@RequestMapping(method = RequestMethod.POST,
			value = "/api/menu",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestaurantMenuResponse> createMenu(
	        @ApiParam(name="restaurantMenu", value = "The restaurant menu.", required = true)
            @RequestBody RestaurantMenuRequest restaurantMenu) {

		RestaurantMenu createNewMenu = restaurantMenuService.create(restaurantMenu);

		return ResponseEntity.created(restaurantMenuURI(createNewMenu.getId()))
				.body(restaurantResponseMapper.toResponse(createNewMenu));
	}

	@ApiOperation("Updates an existing menu.")
	@RequestMapping(method = RequestMethod.PUT, value = "/api/menu/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestaurantMenuResponse> updateMenu(
			@ApiParam(name="id", value = "The ID of the menu.", required = true) @PathVariable ObjectId id,
			@ApiParam(name="restaurantMenu", value = "The restaurant menu.", required = true)
			@RequestBody RestaurantMenuRequest restaurantMenu) {

		RestaurantMenu updatedRestaurantMenu = restaurantMenuService.udpateRestaurantMenu(restaurantMenu, id);

		return ResponseEntity.created(restaurantMenuURI(updatedRestaurantMenu.getId()))
				.body(restaurantResponseMapper.toResponse(updatedRestaurantMenu));
	}

    @ApiOperation("Remove a menu by the given ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Menu not found."),
            @ApiResponse(code = 200, message = "OK") })
    @RequestMapping(method = RequestMethod.DELETE, value = "/api/menu/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeMenu(
            @ApiParam(name="id", value = "The ID of the menu.", required = true)
            @PathVariable ObjectId id) {
	    restaurantMenuService.removeOne(id);
	    return ResponseEntity.status(HttpStatus.OK).build();

    }

	private static URI restaurantMenuURI(ObjectId studentId) {
		return toUri("/api/menu/{id}", studentId);
	}

	private static URI toUri(String path, ObjectId studentId) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path(path)
				.buildAndExpand(studentId).toUri();
	}


}