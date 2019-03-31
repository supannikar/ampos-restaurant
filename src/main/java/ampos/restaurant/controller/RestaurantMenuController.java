package ampos.restaurant.controller;

import ampos.restaurant.controller.request.RestaurantMenuRequest;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(tags = "Menu")
@RestController
public class RestaurantMenuController {

	@Autowired
	private RestaurantMenuService restaurantMenuService;

	/**
	 * Get a Menu by ID.
	 * 
	 * @param menuId
	 * @return
	 */
	@ApiOperation("Retrieves a menu by the given ID.")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Menu not found."),
			@ApiResponse(code = 200, message = "OK") })
	@RequestMapping(method = RequestMethod.GET, value = "/api/menu/{menuId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestaurantMenu getMenu(
			@ApiParam(name="menuId", value = "The ID of the menu.", required = true)
			@PathVariable ObjectId menuId) {
		return restaurantMenuService.findbyId(menuId);

	}

//	/**
//	 * Get student's courses.
//	 *
//	 * @param studentId
//	 *            the ID of the student
//	 * @return
//	 */
//	@ApiOperation("Retrieves the courses in which is enrolled a student.")
//	@RequestMapping(method = RequestMethod.GET, value = "/api/students/{studentId}/courses", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Collection<Course> getStudentCourses(
//			@ApiParam(name="studentId", value = "The ID of the student.", required = true)
//			@PathVariable Integer studentId) {
//		return requireNotNull(studentRepository.findOne(studentId), studentId)
//				.getCourses();
//	}

	@ApiOperation("Creates a new menu.")
	@RequestMapping(method = RequestMethod.POST,
			value = "/api/menu",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestaurantMenu> createMenu(
			@ApiParam(name="restaurantMenu", value = "The restaurant menu.", required = true)
			@RequestBody RestaurantMenuRequest restaurantMenu) {

		RestaurantMenu createNewMenu = restaurantMenuService.create(restaurantMenu);

		return ResponseEntity.created(restaurantMenuURI(createNewMenu.getId())).body(
				createNewMenu);
	}

	@ApiOperation("Updates an existing menu.")
	@RequestMapping(method = RequestMethod.PUT, value = "/api/menu/{menuId}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestaurantMenu> updateMenu(
			@ApiParam(name="menuId", value = "The ID of the menu.", required = true)
			@PathVariable ObjectId menuId,
			@ApiParam(name="restaurantMenu", value = "The restaurant menu.", required = true)
			@RequestBody RestaurantMenuRequest restaurantMenu) {

		RestaurantMenu updatedRestaurantMenu = restaurantMenuService.udpateRestaurantMenu(restaurantMenu, menuId);

		return ResponseEntity.created(restaurantMenuURI(updatedRestaurantMenu.getId())).body(
                updatedRestaurantMenu);
	}

//	/**
//	 * Patches the courses in which the student is enrolled.
//	 *
//	 * @param studentId
//	 * @param courses
//	 * @return
//	 */
//	@ApiOperation("Patches the courses in which is enrolled a student.")
//	@RequestMapping(method = RequestMethod.PATCH, value = "/api/students/{studentId}/courses", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Void> updateStudentCourses(
//			@ApiParam(name="studentId", value = "The ID of the student.", required = true)
//			@PathVariable Integer studentId,
//			@ApiParam(name="courses", value = "A collection of courses in which the student is assigned.", required = true)
//			@RequestBody Collection<Course> courses) {
//
//		Student student = requireNotNull(studentRepository.findOne(studentId),
//				studentId);
//
//		restaurantMenuService.updateStudentCourses(student, courses);
//
//		return ResponseEntity.noContent().location(coursesURI(studentId))
//				.build();
//	}

	private static URI restaurantMenuURI(ObjectId studentId) {
		return toUri("/api/menu/{id}", studentId);
	}

	private static URI toUri(String path, ObjectId studentId) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path(path)
				.buildAndExpand(studentId).toUri();
	}


}