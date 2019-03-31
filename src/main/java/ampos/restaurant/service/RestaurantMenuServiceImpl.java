package ampos.restaurant.service;

import ampos.restaurant.controller.request.RestaurantMenuRequest;
import ampos.restaurant.domain.RestaurantMenu;
import ampos.restaurant.exceptions.NotFoundException;
import ampos.restaurant.repository.RestaurantMenuRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static java.util.Objects.nonNull;

@Service
public class RestaurantMenuServiceImpl implements RestaurantMenuService {

	@Autowired
	private RestaurantMenuRepository restaurantMenuRepository;


	@Override
	public RestaurantMenu create(RestaurantMenuRequest restaurantMenu) {
		RestaurantMenu menu = new RestaurantMenu().setName(restaurantMenu.getName())
				.setDescription(restaurantMenu.getDescription())
				.setImage(restaurantMenu.getImage())
				.setPrice(restaurantMenu.getPrice())
				.setAdditional(restaurantMenu.getAdditional())
				.setCreatedAt(Instant.now())
				.setCreatedBy("");
		return restaurantMenuRepository.save(menu);
	}

	@Override
	public RestaurantMenu udpateRestaurantMenu(RestaurantMenuRequest restaurantMenu, ObjectId menuId) {
		
		RestaurantMenu existingMenu = restaurantMenuRepository.findOne(menuId);
		if (nonNull(existingMenu)) {
			throw new NotFoundException("Menu with Id " + existingMenu.getId() + " not found!");
		}

		if (nonNull(restaurantMenu.getImage())) {
			existingMenu.setImage(restaurantMenu.getImage());
		}

		if (nonNull(restaurantMenu.getDescription())) {
			existingMenu.setDescription(restaurantMenu.getDescription());
		}

		if (nonNull(restaurantMenu.getName())) {
			existingMenu.setName(restaurantMenu.getName());
		}

		if (nonNull(restaurantMenu.getAdditional())) {
			existingMenu.setAdditional(restaurantMenu.getAdditional());
		}

		if (nonNull(restaurantMenu.getAdditional())) {
			existingMenu.setPrice(restaurantMenu.getPrice());
		}

		existingMenu.setModifiedBy("").setModifiedAt(Instant.now());
		
		return  restaurantMenuRepository.save(existingMenu);
		
	}

	@Override
	public RestaurantMenu findbyId(ObjectId id) {

		RestaurantMenu foundMenu = restaurantMenuRepository.findOne(id);
		if (nonNull(foundMenu)) {
			throw new NotFoundException("Menu with Id " + foundMenu.getId() + " not found!");
		}

		return  foundMenu;

	}

}
