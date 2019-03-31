package ampos.restaurant.controller.request;

import org.bson.types.ObjectId;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.Map;

public class BillOrderRequest {

    private Integer billNo;
    private String menuItem;
    private Integer quantity;

    public Integer getBillNo() {
        return billNo;
    }

    public BillOrderRequest setBillNo(Integer billNo) {
        this.billNo = billNo;
        return this;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public BillOrderRequest setMenuItem(String menuItem) {
        this.menuItem = menuItem;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BillOrderRequest setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
