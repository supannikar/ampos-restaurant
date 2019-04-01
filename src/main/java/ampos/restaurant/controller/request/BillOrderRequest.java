package ampos.restaurant.controller.request;

import java.util.List;

public class BillOrderRequest {

    private List<MenuItemRequest> menuItem;

    public List<MenuItemRequest> getMenuItem() {
        return menuItem;
    }

    public BillOrderRequest setMenuItem(List<MenuItemRequest> menuItem) {
        this.menuItem = menuItem;
        return this;
    }
}
