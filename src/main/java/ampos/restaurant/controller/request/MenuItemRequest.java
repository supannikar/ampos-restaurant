package ampos.restaurant.controller.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;

public class MenuItemRequest {
    private String id;

    @NotNull
    @ApiModelProperty(required = true, value = "Quantity of product in cart item (default: 1)")
    @DefaultValue("1")
    private Integer quantity;

    @NotNull
    @ApiModelProperty(required = true,
            value = "Option for updating or adding quantity (default: UPDATE)")
    @DefaultValue("UPDATE")
    private LineItemAction action;

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

    public LineItemAction getAction() {
        return action;
    }

    public MenuItemRequest setAction(final LineItemAction action) {
        this.action = action;
        return this;
    }

    public enum LineItemAction {
        UPDATE,
        ADD,
        DELETE
    }
}
