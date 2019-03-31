package ampos.restaurant.controller.response;

import java.util.List;
import java.util.Map;

public class RestaurantMenuResponse {

    private String id;
    private String name;
    private String description;
    private String image;
    private Double price;
    private Map<String, List<String>> additional;

    public String getId() {
        return id;
    }

    public RestaurantMenuResponse setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RestaurantMenuResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public RestaurantMenuResponse setPrice(Double price) {
        this.price = price;
        return this;
    }

    public RestaurantMenuResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public RestaurantMenuResponse setImage(String image) {
        this.image = image;
        return this;
    }

    public Map<String, List<String>> getAdditional() {
        return additional;
    }

    public RestaurantMenuResponse setAdditional(Map<String, List<String>> additional) {
        this.additional = additional;
        return this;
    }
}
