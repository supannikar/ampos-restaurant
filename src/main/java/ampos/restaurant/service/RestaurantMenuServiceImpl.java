package ampos.restaurant.service;

import ampos.restaurant.controller.mapper.RestaurantResponseMapper;
import ampos.restaurant.controller.request.RestaurantMenuRequest;
import ampos.restaurant.controller.response.ListResponseData;
import ampos.restaurant.controller.response.RestaurantMenuResponse;
import ampos.restaurant.domain.RestaurantMenu;
import ampos.restaurant.exceptions.BadRequestException;
import ampos.restaurant.exceptions.NotFoundException;
import ampos.restaurant.repository.RestaurantMenuRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class RestaurantMenuServiceImpl implements RestaurantMenuService {

	@Autowired
	private RestaurantMenuRepository restaurantMenuRepository;

	@Autowired
	private RestaurantResponseMapper restaurantResponseMapper;

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
	public RestaurantMenu udpateRestaurantMenu(RestaurantMenuRequest restaurantMenu,
                                               ObjectId id) {
		
		RestaurantMenu existingMenu = restaurantMenuRepository.findOne(id);

		if (isNull(existingMenu)) {
			throw new NotFoundException("Menu with Id " + id + " not found!");
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

		if (nonNull(restaurantMenu.getPrice())) {
			existingMenu.setPrice(restaurantMenu.getPrice());
		}

		existingMenu.setModifiedBy("").setModifiedAt(Instant.now());
		
		return  restaurantMenuRepository.save(existingMenu);
		
	}

	@Override
	public RestaurantMenu findbyId(ObjectId id) {

		RestaurantMenu foundMenu = restaurantMenuRepository.findOne(id);
		if (isNull(foundMenu)) {
			throw new NotFoundException("Menu with Id " + id + " not found!");
		}
		return  foundMenu;
	}

	@Override
	public ListResponseData listAll(int page,
										int pageSize,
										String name) {
		List<RestaurantMenu> restaurantMenus = restaurantMenuRepository.findAll();
//				.stream()
//				.filter(restaurantMenu -> restaurantMenu.getDescription().toLowerCase().contains(query)
//						|| restaurantMenu.getName().toLowerCase().contains(query))
//				.collect(Collectors.toList());
//		if (nonNull(name)) {
//			restaurantMenus = restaurantMenus.stream()
//					.map(restaurantMenu -> restaurantMenu.getName().toLowerCase(Locale.ENGLISH).contains(name)).findAny()
//					.collect(Collectors.toList());
//		}
//		page = page - 1;
		ListResponseData listResponseData = new ListResponseData();
		int numberOfPage = (int) Math.ceil((double) restaurantMenus.size() / (double)pageSize);
		if (page < numberOfPage) {
			if (restaurantMenus.size() < pageSize) {
//				return restaurantMenus;
				List<RestaurantMenuResponse> responses = restaurantMenus.stream()
						.map(restaurantMenu -> restaurantResponseMapper.toResponse(restaurantMenu))
						.collect(Collectors.toList());
				return new ListResponseData().setData(responses).setTotal(restaurantMenus.size());

			} else {
				int fromIndex = page * pageSize;
				int toIndex = Math.min(++page * pageSize, restaurantMenus.size());
				List<RestaurantMenuResponse> responses =
						restaurantMenus.subList(fromIndex, toIndex)
						.stream()
						.map(restaurantMenu -> restaurantResponseMapper.toResponse(restaurantMenu))
						.collect(Collectors.toList());
//				return restaurantMenus.subList(fromIndex, toIndex);
				return new ListResponseData().setData(responses).setTotal(restaurantMenus.size());
			}


		} else {
			throw new BadRequestException("Invalid page number: " + numberOfPage);
		}
	}

    @Override
    public void removeOne(ObjectId id) {
	    RestaurantMenu restaurantMenu = restaurantMenuRepository.findOne(id);
	    if (isNull(restaurantMenu)) {
            throw new NotFoundException("Menu with Id " + id + " not found!");
        }
	    restaurantMenuRepository.delete(id);
    }

}
