package ampos.restaurant.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Entity(value = "RestaurantMenu", noClassnameStored = true)
public class RestaurantMenu {
    @Id
    private ObjectId id;
    private String name;
    private String description;
    private String image;
    private Double price;
    private Map<String, String> additional;
    private Instant modifiedAt;
    private String modifiedBy;
    private Instant createdAt;
    private String createdBy;

    public ObjectId getId() {
        return id;
    }

    public RestaurantMenu setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public RestaurantMenu setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getName() {
        return name;
    }

    public RestaurantMenu setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RestaurantMenu setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public RestaurantMenu setImage(String image) {
        this.image = image;
        return this;
    }

    public Map<String, String> getAdditional() {
        return additional;
    }

    public RestaurantMenu setAdditional(Map<String, String> additional) {
        this.additional = additional;
        return this;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public RestaurantMenu setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public RestaurantMenu setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public RestaurantMenu setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RestaurantMenu setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}
