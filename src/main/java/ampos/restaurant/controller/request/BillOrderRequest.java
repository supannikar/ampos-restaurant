package ampos.restaurant.controller.request;

public class BillOrderRequest {

    private String id;
    private Integer quantity;

    public String getId() {
        return id;
    }

    public BillOrderRequest setId(final String id) {
        this.id = id;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BillOrderRequest setQuantity(final Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
