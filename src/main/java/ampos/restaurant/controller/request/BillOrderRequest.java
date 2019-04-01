package ampos.restaurant.controller.request;

import java.util.List;

public class BillOrderRequest {

    private Integer billNo;
    private List<MenuItemRequest> menuItem;

    public Integer getBillNo() {
        return billNo;
    }

    public BillOrderRequest setBillNo(Integer billNo) {
        this.billNo = billNo;
        return this;
    }

    public List<MenuItemRequest> getMenuItem() {
        return menuItem;
    }

    public BillOrderRequest setMenuItem(List<MenuItemRequest> menuItem) {
        this.menuItem = menuItem;
        return this;
    }
}
