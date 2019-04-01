package ampos.restaurant.controller.request;

import io.swagger.annotations.ApiModelProperty;

public class MenuItemRequest {
    private String id;
    private Integer quantity;
    private String operation;

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

    public String getOperation() {
        return operation;
    }

    public MenuItemRequest setOperation(String operation) {
        this.operation = operation;
        return this;
    }
}
