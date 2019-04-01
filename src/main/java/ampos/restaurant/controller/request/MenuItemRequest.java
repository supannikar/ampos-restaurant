package ampos.restaurant.controller.request;

public class MenuItemRequest {
    private String id;
    private Integer quantity;

    public String getId() {
        return id;
    }

    public MenuItemRequest setId(final String id) {
        this.id = id;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public MenuItemRequest setQuantity(final Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
