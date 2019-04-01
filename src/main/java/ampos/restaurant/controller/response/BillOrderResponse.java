package ampos.restaurant.controller.response;

import java.time.Instant;

public class BillOrderResponse {

    private Integer billNo;
    private String menuItem;
    private Integer quantity;
    private Instant orderTime;
    private Double totalPrice;

    public Integer getBillNo() {
        return billNo;
    }

    public BillOrderResponse setBillNo(Integer billNo) {
        this.billNo = billNo;
        return this;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public BillOrderResponse setMenuItem(String menuItem) {
        this.menuItem = menuItem;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BillOrderResponse setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Instant getOrderTime() {
        return orderTime;
    }

    public BillOrderResponse setOrderTime(Instant orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public BillOrderResponse setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
