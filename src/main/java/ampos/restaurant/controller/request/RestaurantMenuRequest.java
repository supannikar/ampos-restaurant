package ampos.restaurant.controller.request;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.time.Instant;
import java.util.Map;

public class RestaurantMenuRequest {

    private String name;
    private String description;
    private String image;
    private Double price;
    private Map<String, String> additional;

    public String getName() {
        return name;
    }

    public RestaurantMenuRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public RestaurantMenuRequest setPrice(Double price) {
        this.price = price;
        return this;
    }

    public RestaurantMenuRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public RestaurantMenuRequest setImage(String image) {
        this.image = image;
        return this;
    }

    public Map<String, String> getAdditional() {
        return additional;
    }

    public RestaurantMenuRequest setAdditional(Map<String, String> additional) {
        this.additional = additional;
        return this;
    }
}
